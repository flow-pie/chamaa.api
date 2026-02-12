package com.chamaa.security.filter;

import com.chamaa.security.dto.VerifiedFirebaseUser;
import com.chamaa.services.FirebaseTokenVerificationService;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class FirebaseAuthenticationFilterTest {

    @Mock
    private FirebaseTokenVerificationService tokenVerificationService;

    @Mock
    private FilterChain filterChain;

    @InjectMocks
    private FirebaseAuthenticationFilter firebaseAuthenticationFilter;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        SecurityContextHolder.clearContext();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    private FirebaseToken createMockToken() {
        FirebaseToken token = mock(FirebaseToken.class);
        when(token.getUid()).thenReturn("test-uid-123");
        when(token.getEmail()).thenReturn("test@example.com");
        return token;
    }

    @Test
    void testDoFilterInternal_ValidBearerToken_SetsAuthentication() throws Exception {
        // Given
        String token = "valid-firebase-token";
        request.addHeader("Authorization", "Bearer " + token);
        FirebaseToken mockToken = createMockToken();
        Map<String, Object> claims = new HashMap<>();
        claims.put("phone_number", "+1234567890");
        when(mockToken.getClaims()).thenReturn(claims);
        when(tokenVerificationService.verifyToken(token)).thenReturn(mockToken);

        // When
        firebaseAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Then
        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
        VerifiedFirebaseUser user = (VerifiedFirebaseUser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        assertEquals("test-uid-123", user.getUid());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("+1234567890", user.getPhoneNumber());
        verify(filterChain, times(1)).doFilter(request, response);
    }

    @Test
    void testDoFilterInternal_NoAuthorizationHeader_ContinuesChain() throws Exception {
        // Given - no Authorization header

        // When
        firebaseAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Then
        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain, times(1)).doFilter(request, response);
        verify(tokenVerificationService, never()).verifyToken(anyString());
    }

    @Test
    void testDoFilterInternal_InvalidTokenFormat_ContinuesChain() throws Exception {
        // Given
        request.addHeader("Authorization", "InvalidFormat token");

        // When
        firebaseAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Then
        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain, times(1)).doFilter(request, response);
        verify(tokenVerificationService, never()).verifyToken(anyString());
    }

    @Test
    void testDoFilterInternal_InvalidToken_Returns401() throws Exception {
        // Given
        String token = "invalid-token";
        request.addHeader("Authorization", "Bearer " + token);
        FirebaseAuthException exception = mock(FirebaseAuthException.class);
        when(exception.getErrorCode()).thenReturn(null); // Will result in AUTH_FAILED
        when(exception.getMessage()).thenReturn("Token is invalid");
        when(tokenVerificationService.verifyToken(token)).thenThrow(exception);

        // When
        firebaseAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Then
        assertEquals(HttpServletResponse.SC_UNAUTHORIZED, response.getStatus());
        assertTrue(response.getContentType().startsWith("application/json"));
        String responseBody = response.getContentAsString();
        assertTrue(responseBody.contains("\"error\":\"Unauthorized\""));
        assertTrue(responseBody.contains("\"error_code\""));
        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain, never()).doFilter(any(), any());
    }

    @Test
    void testDoFilterInternal_ExpiredToken_Returns401() throws Exception {
        // Given
        String token = "expired-token";
        request.addHeader("Authorization", "Bearer " + token);
        FirebaseAuthException exception = mock(FirebaseAuthException.class);
        when(exception.getErrorCode()).thenReturn(null); // Will result in AUTH_FAILED
        when(exception.getMessage()).thenReturn("Token has expired");
        when(tokenVerificationService.verifyToken(token)).thenThrow(exception);

        // When
        firebaseAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Then
        assertEquals(HttpServletResponse.SC_UNAUTHORIZED, response.getStatus());
        String responseBody = response.getContentAsString();
        assertTrue(responseBody.contains("\"error_code\""));
        assertTrue(responseBody.contains("expired"));
        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain, never()).doFilter(any(), any());
    }

    @Test
    void testDoFilterInternal_TokenWithPhoneNumber_ExtractsPhoneNumber() throws Exception {
        // Given
        String token = "valid-token";
        request.addHeader("Authorization", "Bearer " + token);
        FirebaseToken mockToken = createMockToken();
        Map<String, Object> claims = new HashMap<>();
        claims.put("phone_number", "+9876543210");
        when(mockToken.getClaims()).thenReturn(claims);
        when(tokenVerificationService.verifyToken(token)).thenReturn(mockToken);

        // When
        firebaseAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Then
        VerifiedFirebaseUser user = (VerifiedFirebaseUser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        assertEquals("+9876543210", user.getPhoneNumber());
    }

    @Test
    void testDoFilterInternal_TokenWithoutPhoneNumber_HandlesNull() throws Exception {
        // Given
        String token = "valid-token";
        request.addHeader("Authorization", "Bearer " + token);
        FirebaseToken mockToken = createMockToken();
        Map<String, Object> claims = new HashMap<>();
        when(mockToken.getClaims()).thenReturn(claims);
        when(tokenVerificationService.verifyToken(token)).thenReturn(mockToken);

        // When
        firebaseAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Then
        VerifiedFirebaseUser user = (VerifiedFirebaseUser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        assertNull(user.getPhoneNumber());
    }

    @Test
    void testDoFilterInternal_TokenWithoutEmail_HandlesNull() throws Exception {
        // Given
        String token = "valid-token";
        request.addHeader("Authorization", "Bearer " + token);
        FirebaseToken tokenWithoutEmail = mock(FirebaseToken.class);
        when(tokenWithoutEmail.getUid()).thenReturn("test-uid");
        when(tokenWithoutEmail.getEmail()).thenReturn(null);
        when(tokenWithoutEmail.getClaims()).thenReturn(new HashMap<>());
        when(tokenVerificationService.verifyToken(token)).thenReturn(tokenWithoutEmail);

        // When
        firebaseAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Then
        VerifiedFirebaseUser user = (VerifiedFirebaseUser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        assertNull(user.getEmail());
        assertEquals("test-uid", user.getUid());
    }

    @Test
    void testExtractToken_ValidBearerHeader_ReturnsToken() throws Exception {
        // Given
        String token = "test-token-123";
        request.addHeader("Authorization", "Bearer " + token);
        FirebaseToken mockToken = createMockToken();
        when(mockToken.getClaims()).thenReturn(new HashMap<>());
        when(tokenVerificationService.verifyToken(token)).thenReturn(mockToken);

        // When
        firebaseAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Then - verify token was extracted and used
        verify(tokenVerificationService, times(1)).verifyToken(token);
        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void testExtractToken_NoBearerPrefix_ReturnsNull() throws Exception {
        // Given
        request.addHeader("Authorization", "Basic dGVzdDp0ZXN0");

        // When
        firebaseAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Then
        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(tokenVerificationService, never()).verifyToken(anyString());
    }

    @Test
    void testExtractToken_NoHeader_ReturnsNull() throws Exception {
        // Given - no header

        // When
        firebaseAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Then
        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(tokenVerificationService, never()).verifyToken(anyString());
    }

    @Test
    void testSendUnauthorized_SetsCorrectResponse() throws Exception {
        // Given
        String token = "invalid-token";
        request.addHeader("Authorization", "Bearer " + token);
        FirebaseAuthException exception = mock(FirebaseAuthException.class);
        when(exception.getErrorCode()).thenReturn(null); // Will result in AUTH_FAILED
        when(exception.getMessage()).thenReturn("Test error message");
        when(tokenVerificationService.verifyToken(token)).thenThrow(exception);

        // When
        firebaseAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Then
        assertEquals(HttpServletResponse.SC_UNAUTHORIZED, response.getStatus());
        assertTrue(response.getContentType().startsWith("application/json"));
        String responseBody = response.getContentAsString();
        assertTrue(responseBody.contains("\"error\":\"Unauthorized\""));
        assertTrue(responseBody.contains("\"error_code\""));
        assertTrue(responseBody.contains("\"message\":\"Test error message\""));
    }

    @Test
    void testSendUnauthorized_HandlesNullErrorCode() throws Exception {
        // Given
        String token = "invalid-token";
        request.addHeader("Authorization", "Bearer " + token);
        FirebaseAuthException exception = mock(FirebaseAuthException.class);
        when(exception.getErrorCode()).thenReturn(null);
        when(exception.getMessage()).thenReturn("Error message");
        when(tokenVerificationService.verifyToken(token)).thenThrow(exception);

        // When
        firebaseAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Then
        assertEquals(HttpServletResponse.SC_UNAUTHORIZED, response.getStatus());
        String responseBody = response.getContentAsString();
        assertTrue(responseBody.contains("\"error_code\":\"AUTH_FAILED\""));
    }
}
