package com.chamaa.security.dto;

/**
 * DTO holding verified user details extracted from a valid Firebase ID token.
 */
public class VerifiedFirebaseUser {

    private final String uid;
    private final String email;
    private final String phoneNumber;

    public VerifiedFirebaseUser(String uid, String email, String phoneNumber) {
        this.uid = uid;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
