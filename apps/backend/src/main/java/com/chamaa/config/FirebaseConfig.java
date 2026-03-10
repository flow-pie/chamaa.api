package com.chamaa.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    private static final Logger logger = LoggerFactory.getLogger(FirebaseConfig.class);

    @Value("${firebase.project-id:}")
    private String projectId;

    @Value("${firebase.credentials-path:}")
    private String credentialsPath;

    @PostConstruct
    public void initializeFirebase() {
        if (credentialsPath == null || credentialsPath.isBlank()) {
            logger.warn("Firebase credentials path not set - skipping Firebase initialization (dev mode)");
            return;
        }
        try {
            if (!FirebaseApp.getApps().isEmpty()) {
                logger.info("Firebase Admin SDK already initialized");
                return;
            }
            try (InputStream serviceAccount = new FileInputStream(credentialsPath)) {
                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .setProjectId(projectId)
                        .build();
                FirebaseApp.initializeApp(options);
                logger.info("Firebase Admin SDK initialized successfully for project: {}", projectId);
            }
        } catch (IOException e) {
            logger.error("Failed to initialize Firebase Admin SDK", e);
            throw new RuntimeException("Firebase initialization failed", e);
        } catch (IllegalStateException e) {
            logger.warn("Firebase initialization skipped: {}", e.getMessage());
        }
    }

    @Bean
    public FirebaseAuth firebaseAuth() {
        if (FirebaseApp.getApps().isEmpty()) {
            return null;
        }
        return FirebaseAuth.getInstance();
    }
}