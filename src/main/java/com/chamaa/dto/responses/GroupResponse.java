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
public class GroupResponse {

    private Long id;

    private String name;

    private String description;

    private String groupCode;

    private BigDecimal targetAmount;

    private BigDecimal currentAmount;

    private BigDecimal contributionAmount;

    private String currency;

    private String status;

    private Integer memberCount;

    private Long createdBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
