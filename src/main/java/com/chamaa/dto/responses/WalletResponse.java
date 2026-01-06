package com.chamaa.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WalletResponse {

    private Long id;

    private Long userId;

    private BigDecimal balance;

    private BigDecimal totalContributions;

    private BigDecimal totalLoansIssued;

    private BigDecimal totalLoansRepaid;

    private String walletAddress;

    private String currency;

    private LocalDateTime lastTransactionAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
