package com.chamaa.payments;

import java.math.BigDecimal;

public class PaymentRequest {
    private Long userId;
    private BigDecimal amount;
    private String paymentMethod;
    private String phoneNumber;
    private String accountReference;
    private String description;

    public PaymentRequest() {
    }

    public PaymentRequest(Long userId, BigDecimal amount, String paymentMethod, String phoneNumber,
                        String accountReference, String description) {
        this.userId = userId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.phoneNumber = phoneNumber;
        this.accountReference = accountReference;
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAccountReference() {
        return accountReference;
    }

    public void setAccountReference(String accountReference) {
        this.accountReference = accountReference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
