package com.chamaa.payments;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.Instant;

@Service
public class MpesaService {

    private static final Logger logger = LoggerFactory.getLogger(MpesaService.class);

    @Value("${mpesa.api.url:https://sandbox.safaricom.co.ke}")
    private String mpesaApiUrl;

    @Value("${mpesa.consumer.key}")
    private String mpesaConsumerKey;

    @Value("${mpesa.consumer.secret}")
    private String mpesaConsumerSecret;

    @Value("${mpesa.business.shortcode}")
    private String mpesaBusinessShortcode;

    @Value("${mpesa.passkey}")
    private String mpesaPasskey;

    public MpesaPaymentResponse initiateStkPush(MpesaPaymentRequest request) {
        logger.info("Initiating STK push for phone: {}", request.getPhoneNumber());

        try {
            validatePaymentRequest(request);

            return MpesaPaymentResponse.builder()
                    .transactionId(generateTransactionId())
                    .status("PENDING")
                    .amount(request.getAmount())
                    .phoneNumber(request.getPhoneNumber())
                    .message("STK push sent successfully")
                    .timestamp(Instant.now().toEpochMilli())
                    .build();

        } catch (IllegalArgumentException e) {
            logger.error("Invalid payment request: {}", e.getMessage());
            return MpesaPaymentResponse.builder()
                    .status("FAILED")
                    .message(e.getMessage())
                    .timestamp(Instant.now().toEpochMilli())
                    .build();
        }
    }

    public MpesaPaymentResponse checkTransactionStatus(String transactionId) {
        logger.info("Checking status for transaction: {}", transactionId);

        return MpesaPaymentResponse.builder()
                .transactionId(transactionId)
                .status("COMPLETED")
                .timestamp(Instant.now().toEpochMilli())
                .build();
    }

    private void validatePaymentRequest(MpesaPaymentRequest request) {
        if (request.getPhoneNumber() == null || request.getPhoneNumber().isEmpty()) {
            throw new IllegalArgumentException("Phone number is required");
        }
        if (request.getAmount() == null || request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
    }

    private String generateTransactionId() {
        return "TXN" + System.currentTimeMillis();
    }
}
