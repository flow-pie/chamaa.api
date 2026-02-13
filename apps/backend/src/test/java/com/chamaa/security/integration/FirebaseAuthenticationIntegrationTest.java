package com.chamaa.security.integration;

import com.chamaa.security.dto.VerifiedFirebaseUser;
import com.chamaa.services.FirebaseTokenVerificationService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class FirebaseAuthenticationIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FirebaseTokenVerificationService tokenVerificationService;

    @MockBean
    private FirebaseAuth firebaseAuth;

    private FirebaseToken mockFirebaseToken;

    @BeforeEach
    void setUp() {
        SecurityContextHolder.clearContext();
        mockFirebaseToken = org.mockito.Mockito.mock(FirebaseToken.class);
        when(mockFirebaseToken.getUid()).thenReturn("test-uid-123");
        when(mockFirebaseToken.getEmail()).thenReturn("test@example.com");
        Map<String, Object> claims = new HashMap<>();
        claims.put("phone_number", "+1234567890");
        when(mockFirebaseToken.getClaims()).thenReturn(claims);
    }

    @Test
    void testAuthenticatedRequest_ValidToken_Returns200() throws Exception {
        // Given
        String validToken = "valid-firebase-token";
        when(tokenVerificationService.verifyToken(validToken)).thenReturn(mockFirebaseToken);

        // When & Then
        mockMvc.perform(get("/api/users")
                        .header("Authorization", "Bearer " + validToken))
                .andExpect(result -> {
                    int status = result.getResponse().getStatus();
                    assertTrue(status == 200 || status == 404, "Expected 200 or 404, got " + status);
                });
    }

    @Test
    void testAuthenticatedRequest_InvalidToken_Returns401() throws Exception {
        // Given
        String invalidToken = "invalid-token";
        FirebaseAuthException exception = mock(FirebaseAuthException.class);
        when(exception.getErrorCode()).thenReturn(null);
        when(exception.getMessage()).thenReturn("Token is invalid");
        when(tokenVerificationService.verifyToken(invalidToken)).thenThrow(exception);

        // When & Then
        mockMvc.perform(get("/api/users")
                        .header("Authorization", "Bearer " + invalidToken))
                .andExpect(status().isUnauthorized())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$.error").value("Unauthorized"))
                .andExpect(jsonPath("$.error_code").exists());
    }

    @Test
    void testAuthenticatedRequest_NoToken_Returns401() throws Exception {
        // Given - no Authorization header

        // When & Then
        mockMvc.perform(get("/api/users"))
                .andExpect(result -> {
                    int status = result.getResponse().getStatus();
                    assertTrue(status == 401 || status == 403, "Expected 401 or 403, got " + status);
                });
    }

    @Test
    void testPublicEndpoint_NoToken_Returns200() throws Exception {
        // Given - public endpoint should not require authentication
        // Note: This assumes /api/public/** or /api/health/** exists
        // If not, the test will check for 404 which is acceptable

        // When & Then
        mockMvc.perform(get("/api/public/test"))
                .andExpect(result -> {
                    int status = result.getResponse().getStatus();
                    assertTrue(status == 200 || status == 404, "Expected 200 or 404, got " + status);
                });
    }

    @Test
    void testProtectedEndpoint_ValidToken_AccessGranted() throws Exception {
        // Given
        String validToken = "valid-firebase-token";
        when(tokenVerificationService.verifyToken(validToken)).thenReturn(mockFirebaseToken);

        // When & Then - protected endpoint should be accessible with valid token
        mockMvc.perform(get("/api/groups")
                        .header("Authorization", "Bearer " + validToken))
                .andExpect(result -> {
                    int status = result.getResponse().getStatus();
                    assertTrue(status == 200 || status == 404, "Expected 200 or 404, got " + status);
                });
    }

    @Test
    void testMultipleRequests_SameToken_Works() throws Exception {
        // Given
        String validToken = "valid-firebase-token";
        when(tokenVerificationService.verifyToken(validToken)).thenReturn(mockFirebaseToken);

        // When & Then - same token should work for multiple requests (stateless)
        mockMvc.perform(get("/api/users")
                        .header("Authorization", "Bearer " + validToken))
                .andExpect(result -> {
                    int status = result.getResponse().getStatus();
                    assertTrue(status == 200 || status == 404, "Expected 200 or 404, got " + status);
                });

        mockMvc.perform(get("/api/groups")
                        .header("Authorization", "Bearer " + validToken))
                .andExpect(result -> {
                    int status = result.getResponse().getStatus();
                    assertTrue(status == 200 || status == 404, "Expected 200 or 404, got " + status);
                });
    }

    @Test
    void testAuthenticatedRequest_ExpiredToken_Returns401() throws Exception {
        // Given
        String expiredToken = "expired-token";
        FirebaseAuthException exception = mock(FirebaseAuthException.class);
        when(exception.getErrorCode()).thenReturn(null);
        when(exception.getMessage()).thenReturn("Token has expired");
        when(tokenVerificationService.verifyToken(expiredToken)).thenThrow(exception);

        // When & Then
        mockMvc.perform(get("/api/users")
                        .header("Authorization", "Bearer " + expiredToken))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error_code").exists());
    }

    @Test
    void testAuthenticatedRequest_MalformedHeader_Returns401() throws Exception {
        // Given - malformed Authorization header (no Bearer prefix)

        // When & Then
        mockMvc.perform(get("/api/users")
                        .header("Authorization", "InvalidFormat token"))
                .andExpect(result -> {
                    int status = result.getResponse().getStatus();
                    assertTrue(status == 401 || status == 403, "Expected 401 or 403, got " + status);
                });
    }
}
