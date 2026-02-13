package com.chamaa.services;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class FirebaseTokenVerificationServiceTest {

    @Mock
    private FirebaseAuth firebaseAuth;

    @InjectMocks
    private FirebaseTokenVerificationService tokenVerificationService;

    private FirebaseToken mockFirebaseToken;

    @BeforeEach
    void setUp() {
        // Setup is done per test to avoid unnecessary stubbing warnings
    }

    @Test
    void testVerifyToken_ValidToken_ReturnsDecodedToken() throws FirebaseAuthException {
        // Given
        String validToken = "valid-firebase-token";
        FirebaseToken mockToken = mock(FirebaseToken.class);
        when(mockToken.getUid()).thenReturn("test-uid-123");
        when(mockToken.getEmail()).thenReturn("test@example.com");
        when(firebaseAuth.verifyIdToken(validToken)).thenReturn(mockToken);

        // When
        FirebaseToken result = tokenVerificationService.verifyToken(validToken);

        // Then
        assertNotNull(result);
        assertEquals("test-uid-123", result.getUid());
        assertEquals("test@example.com", result.getEmail());
        verify(firebaseAuth, times(1)).verifyIdToken(validToken);
    }

    private FirebaseToken createMockToken() {
        FirebaseToken token = mock(FirebaseToken.class);
        when(token.getUid()).thenReturn("test-uid-123");
        when(token.getEmail()).thenReturn("test@example.com");
        return token;
    }

    @Test
    void testVerifyToken_InvalidToken_ThrowsFirebaseAuthException() throws FirebaseAuthException {
        // Given
        String invalidToken = "invalid-token";
        FirebaseAuthException exception = mock(FirebaseAuthException.class);
        when(exception.getMessage()).thenReturn("Token is invalid");
        when(firebaseAuth.verifyIdToken(invalidToken)).thenThrow(exception);

        // When & Then
        assertThrows(FirebaseAuthException.class, () -> {
            tokenVerificationService.verifyToken(invalidToken);
        });

        verify(firebaseAuth, times(1)).verifyIdToken(invalidToken);
    }

    @Test
    void testVerifyToken_ExpiredToken_ThrowsFirebaseAuthException() throws FirebaseAuthException {
        // Given
        String expiredToken = "expired-token";
        FirebaseAuthException exception = mock(FirebaseAuthException.class);
        when(exception.getMessage()).thenReturn("Token has expired");
        when(firebaseAuth.verifyIdToken(expiredToken)).thenThrow(exception);

        // When & Then
        FirebaseAuthException thrown = assertThrows(FirebaseAuthException.class, () -> {
            tokenVerificationService.verifyToken(expiredToken);
        });

        assertTrue(thrown.getMessage().contains("expired"));
        verify(firebaseAuth, times(1)).verifyIdToken(expiredToken);
    }

    @Test
    void testVerifyToken_RevokedToken_ThrowsFirebaseAuthException() throws FirebaseAuthException {
        // Given
        String revokedToken = "revoked-token";
        FirebaseAuthException exception = mock(FirebaseAuthException.class);
        when(exception.getMessage()).thenReturn("Token has been revoked");
        when(firebaseAuth.verifyIdToken(revokedToken)).thenThrow(exception);

        // When & Then
        FirebaseAuthException thrown = assertThrows(FirebaseAuthException.class, () -> {
            tokenVerificationService.verifyToken(revokedToken);
        });

        assertTrue(thrown.getMessage().contains("revoked"));
        verify(firebaseAuth, times(1)).verifyIdToken(revokedToken);
    }

    @Test
    void testVerifyToken_NullToken_ThrowsException() throws FirebaseAuthException {
        // Given
        FirebaseAuthException exception = mock(FirebaseAuthException.class);
        when(exception.getMessage()).thenReturn("Token cannot be null");
        when(firebaseAuth.verifyIdToken(null)).thenThrow(exception);

        // When & Then
        assertThrows(FirebaseAuthException.class, () -> {
            tokenVerificationService.verifyToken(null);
        });

        verify(firebaseAuth, times(1)).verifyIdToken(null);
    }

    @Test
    void testVerifyToken_EmptyToken_ThrowsException() throws FirebaseAuthException {
        // Given
        String emptyToken = "";
        FirebaseAuthException exception = mock(FirebaseAuthException.class);
        when(exception.getMessage()).thenReturn("Token cannot be empty");
        when(firebaseAuth.verifyIdToken(emptyToken)).thenThrow(exception);

        // When & Then
        assertThrows(FirebaseAuthException.class, () -> {
            tokenVerificationService.verifyToken(emptyToken);
        });

        verify(firebaseAuth, times(1)).verifyIdToken(emptyToken);
    }
}
