package com.chamaa.payments;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

@Service
public class PaymentGatewayService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentGatewayService.class);

    private final MpesaService mpesaService;

    public PaymentGatewayService(MpesaService mpesaService) {
        this.mpesaService = mpesaService;
    }

    public PaymentResponse processPayment(PaymentRequest request) {
        logger.info("Processing payment for user: {}", request.getUserId());

        switch (request.getPaymentMethod().toUpperCase()) {
            case "MPESA":
                return processMpesaPayment(request);
            case "BANK_TRANSFER":
                return processBankTransfer(request);
            default:
                throw new IllegalArgumentException("Unsupported payment method: " + request.getPaymentMethod());
        }
    }

    private PaymentResponse processMpesaPayment(PaymentRequest request) {
        MpesaPaymentRequest mpesaRequest = new MpesaPaymentRequest();
        mpesaRequest.setPhoneNumber(request.getPhoneNumber());
        mpesaRequest.setAmount(request.getAmount());
        mpesaRequest.setAccountReference(request.getAccountReference());
        mpesaRequest.setDescription(request.getDescription());

        MpesaPaymentResponse mpesaResponse = mpesaService.initiateStkPush(mpesaRequest);

        return PaymentResponse.builder()
                .paymentId(mpesaResponse.getTransactionId())
                .status(mpesaResponse.getStatus())
                .amount(mpesaResponse.getAmount())
                .method("MPESA")
                .message(mpesaResponse.getMessage())
                .timestamp(mpesaResponse.getTimestamp())
                .build();
    }

    private PaymentResponse processBankTransfer(PaymentRequest request) {
        logger.info("Processing bank transfer for amount: {}", request.getAmount());

        return PaymentResponse.builder()
                .paymentId("BT" + System.currentTimeMillis())
                .status("PENDING")
                .amount(request.getAmount())
                .method("BANK_TRANSFER")
                .message("Bank transfer initiated. Please complete the transfer.")
                .timestamp(System.currentTimeMillis())
                .build();
    }

    public PaymentResponse getPaymentStatus(String paymentId) {
        logger.info("Fetching payment status for: {}", paymentId);

        if (paymentId.startsWith("TXN")) {
            MpesaPaymentResponse mpesaResponse = mpesaService.checkTransactionStatus(paymentId);
            return PaymentResponse.builder()
                    .paymentId(mpesaResponse.getTransactionId())
                    .status(mpesaResponse.getStatus())
                    .timestamp(mpesaResponse.getTimestamp())
                    .build();
        }

        return PaymentResponse.builder()
                .paymentId(paymentId)
                .status("UNKNOWN")
                .build();
    }
}
