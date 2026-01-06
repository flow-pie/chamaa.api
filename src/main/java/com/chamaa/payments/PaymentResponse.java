package com.chamaa.payments;

import java.math.BigDecimal;

public class PaymentResponse {
    private String paymentId;
    private String status;
    private BigDecimal amount;
    private String method;
    private String message;
    private Long timestamp;

    public PaymentResponse() {
    }

    public PaymentResponse(String paymentId, String status, BigDecimal amount,
                          String method, String message, Long timestamp) {
        this.paymentId = paymentId;
        this.status = status;
        this.amount = amount;
        this.method = method;
        this.message = message;
        this.timestamp = timestamp;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
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

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
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
        private String paymentId;
        private String status;
        private BigDecimal amount;
        private String method;
        private String message;
        private Long timestamp;

        public Builder paymentId(String paymentId) {
            this.paymentId = paymentId;
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

        public Builder method(String method) {
            this.method = method;
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

        public PaymentResponse build() {
            return new PaymentResponse(paymentId, status, amount, method, message, timestamp);
        }
    }
}
