package com.chamaa.security.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VerifiedFirebaseUserTest {

    @Test
    void testConstructor_SetsAllFields() {
        // Given
        String uid = "test-uid-123";
        String email = "test@example.com";
        String phoneNumber = "+1234567890";

        // When
        VerifiedFirebaseUser user = new VerifiedFirebaseUser(uid, email, phoneNumber);

        // Then
        assertEquals(uid, user.getUid());
        assertEquals(email, user.getEmail());
        assertEquals(phoneNumber, user.getPhoneNumber());
    }

    @Test
    void testGetters_ReturnCorrectValues() {
        // Given
        String uid = "uid-456";
        String email = "user@test.com";
        String phoneNumber = "+9876543210";
        VerifiedFirebaseUser user = new VerifiedFirebaseUser(uid, email, phoneNumber);

        // When & Then
        assertEquals(uid, user.getUid());
        assertEquals(email, user.getEmail());
        assertEquals(phoneNumber, user.getPhoneNumber());
    }

    @Test
    void testConstructor_NullValues_HandlesGracefully() {
        // Given
        String uid = "test-uid";
        String email = null;
        String phoneNumber = null;

        // When
        VerifiedFirebaseUser user = new VerifiedFirebaseUser(uid, email, phoneNumber);

        // Then
        assertEquals(uid, user.getUid());
        assertNull(user.getEmail());
        assertNull(user.getPhoneNumber());
    }

    @Test
    void testConstructor_EmptyStrings_HandlesGracefully() {
        // Given
        String uid = "";
        String email = "";
        String phoneNumber = "";

        // When
        VerifiedFirebaseUser user = new VerifiedFirebaseUser(uid, email, phoneNumber);

        // Then
        assertEquals("", user.getUid());
        assertEquals("", user.getEmail());
        assertEquals("", user.getPhoneNumber());
    }
}
