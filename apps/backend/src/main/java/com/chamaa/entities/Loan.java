package com.chamaa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "loans", indexes = {
    @Index(name = "idx_loans_borrower_id", columnList = "borrower_id"),
    @Index(name = "idx_loans_group_id", columnList = "group_id"),
    @Index(name = "idx_loans_status", columnList = "status"),
    @Index(name = "idx_loans_created_at", columnList = "created_at")
})
@Getter
@Setter
public class Loan extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "borrower_id", nullable = false)
    private User borrower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Double interestRate = 0.0;

    @Column(nullable = false, length = 50)
    private String status = "PENDING";

    @Column(columnDefinition = "TEXT")
    private String purpose;

    @Column(nullable = false)
    private Integer durationInMonths;

    @Column(nullable = false)
    private Double paidAmount = 0.0;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getBorrower() {
        return borrower;
    }

    public void setBorrower(User borrower) {
        this.borrower = borrower;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Integer getDurationInMonths() {
        return durationInMonths;
    }

    public void setDurationInMonths(Integer durationInMonths) {
        this.durationInMonths = durationInMonths;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }
}

