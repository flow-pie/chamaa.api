package com.chamaa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "transactions", indexes = {
    @Index(name = "idx_transactions_from_wallet", columnList = "from_wallet_id"),
    @Index(name = "idx_transactions_to_wallet", columnList = "to_wallet_id"),
    @Index(name = "idx_transactions_group", columnList = "group_id"),
    @Index(name = "idx_transactions_status", columnList = "status"),
    @Index(name = "idx_transactions_created_at", columnList = "created_at")
})
@Getter
@Setter
public class Transaction extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_wallet_id", nullable = false)
    private Wallet fromWallet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_wallet_id", nullable = false)
    private Wallet toWallet;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false, length = 50)
    private String status = "PENDING";

    @Column(length = 255)
    private String transactionHash;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;
}

