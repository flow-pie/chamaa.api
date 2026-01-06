package com.chamaa.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt.secret:ChamaaSecretKeyForJWT2024CommunityLendingPlatformSecurityToken}")
    private String jwtSecret;

    @Value("${app.jwt.expiration:86400000}")
    private long jwtExpirationMs;

    private final Map<String, Long> tokenExpirationMap = new HashMap<>();

    public String generateToken(String username) {
        String token = Base64.getEncoder().encodeToString(
                (username + ":" + System.currentTimeMillis()).getBytes(StandardCharsets.UTF_8)
        );
        tokenExpirationMap.put(token, System.currentTimeMillis() + jwtExpirationMs);
        return token;
    }

    public String getUsernameFromToken(String token) {
        try {
            String decoded = new String(Base64.getDecoder().decode(token), StandardCharsets.UTF_8);
            return decoded.split(":")[0];
        } catch (Exception e) {
            return null;
        }
    }

    public boolean validateToken(String token) {
        try {
            if (!tokenExpirationMap.containsKey(token)) {
                return false;
            }
            Long expirationTime = tokenExpirationMap.get(token);
            return expirationTime > System.currentTimeMillis();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isTokenExpired(String token) {
        try {
            Long expirationTime = tokenExpirationMap.get(token);
            if (expirationTime == null) {
                return true;
            }
            return expirationTime < System.currentTimeMillis();
        } catch (Exception e) {
            return true;
        }
    }
}
