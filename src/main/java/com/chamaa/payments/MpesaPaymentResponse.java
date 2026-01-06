package com.chamaa.payments;

import java.math.BigDecimal;

public class MpesaPaymentResponse {
    private String transactionId;
    private String status;
    private BigDecimal amount;
    private String phoneNumber;
    private String message;
    private Long timestamp;

    public MpesaPaymentResponse() {
    }

    public MpesaPaymentResponse(String transactionId, String status, BigDecimal amount,
                                String phoneNumber, String message, Long timestamp) {
        this.transactionId = transactionId;
        this.status = status;
        this.amount = amount;
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.timestamp = timestamp;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public static class Builder {
        private String transactionId;
        private String status;
        private BigDecimal amount;
        private String phoneNumber;
        private String message;
        private Long timestamp;

        public Builder transactionId(String transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder timestamp(Long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public MpesaPaymentResponse build() {
            return new MpesaPaymentResponse(transactionId, status, amount, phoneNumber, message, timestamp);
        }
    }
}
