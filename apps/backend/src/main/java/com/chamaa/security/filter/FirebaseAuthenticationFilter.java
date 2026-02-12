package com.chamaa.security.filter;

import com.chamaa.security.dto.VerifiedFirebaseUser;
import com.chamaa.services.FirebaseTokenVerificationService;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class FirebaseAuthenticationFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    private final FirebaseTokenVerificationService tokenVerificationService;

    public FirebaseAuthenticationFilter(FirebaseTokenVerificationService tokenVerificationService) {
        this.tokenVerificationService = tokenVerificationService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = extractToken(request);

        if (StringUtils.hasText(token)) {
            try {
                FirebaseToken decodedToken = tokenVerificationService.verifyToken(token);

                String phoneNumber = null;
                if (decodedToken.getClaims().containsKey("phone_number")) {
                    Object phone = decodedToken.getClaims().get("phone_number");
                    phoneNumber = phone != null ? phone.toString() : null;
                }

                VerifiedFirebaseUser user = new VerifiedFirebaseUser(
                    decodedToken.getUid(),
                    decodedToken.getEmail(),
                    phoneNumber
                );

                UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
                    );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (FirebaseAuthException e) {
                SecurityContextHolder.clearContext();
                String errorCode = e.getErrorCode() != null ? e.getErrorCode().name() : "AUTH_FAILED";
                sendUnauthorized(response, errorCode, e.getMessage());
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private void sendUnauthorized(HttpServletResponse response, String errorCode, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String safeMessage = message != null ? message.replace("\"", "'") : "Token verification failed";
        String safeCode = errorCode != null ? errorCode : "AUTH_FAILED";
        String body = String.format("{\"error\":\"Unauthorized\",\"error_code\":\"%s\",\"message\":\"%s\"}",
            safeCode, safeMessage);
        response.getWriter().write(body);
    }

    private String extractToken(HttpServletRequest request) {
        String bearer = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearer) && bearer.startsWith(BEARER_PREFIX)) {
            return bearer.substring(BEARER_PREFIX.length());
        }
        return null;
    }
}
