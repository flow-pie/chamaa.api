package com.chamaa.services;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FirebaseTokenVerificationService {

    private static final Logger logger = LoggerFactory.getLogger(FirebaseTokenVerificationService.class);
    private final FirebaseAuth firebaseAuth;

    public FirebaseTokenVerificationService(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }

    /**
     * Verifies Firebase ID token and returns decoded token information.
     *
     * @param idToken Firebase ID token string
     * @return FirebaseToken containing user details (UID, email, phone, etc.)
     * @throws FirebaseAuthException if token is invalid, expired, or revoked
     */
    public FirebaseToken verifyToken(String idToken) throws FirebaseAuthException {
        try {
            FirebaseToken decodedToken = firebaseAuth.verifyIdToken(idToken);
            logger.debug("Successfully verified Firebase token for UID: {}", decodedToken.getUid());
            return decodedToken;
        } catch (FirebaseAuthException e) {
            logger.error("Firebase token verification failed: {}", e.getMessage());
            throw e;
        }
    }
}
