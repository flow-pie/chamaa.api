package com.chamaa.payments;

import java.math.BigDecimal;

public class MpesaPaymentRequest {
    private String phoneNumber;
    private BigDecimal amount;
    private String accountReference;
    private String description;

    public MpesaPaymentRequest() {
    }

    public MpesaPaymentRequest(String phoneNumber, BigDecimal amount, String accountReference, String description) {
        this.phoneNumber = phoneNumber;
        this.amount = amount;
        this.accountReference = accountReference;
        this.description = description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
