package com.chamaa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "loans")
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

    @Column(nullable = false)
    private String status = "PENDING";

    private String purpose;

    @Column(nullable = false)
    private Integer durationInMonths;

    @Column(nullable = false)
    private Double paidAmount = 0.0;
}
