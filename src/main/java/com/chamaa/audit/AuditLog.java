package com.chamaa.audit;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AuditLog {

    private Long id;

    private String action;

    private String entityType;

    private Long entityId;

    private String performedBy;

    private String description;

    private BigDecimal amount;

    private String transactionId;

    private String status;

    private String ipAddress;

    private LocalDateTime timestamp;

    private String details;

    public AuditLog() {
    }

    public AuditLog(Long id, String action, String entityType, Long entityId, String performedBy,
                    String description, BigDecimal amount, String transactionId, String status,
                    String ipAddress, LocalDateTime timestamp, String details) {
        this.id = id;
        this.action = action;
        this.entityType = entityType;
        this.entityId = entityId;
        this.performedBy = performedBy;
        this.description = description;
        this.amount = amount;
        this.transactionId = transactionId;
        this.status = status;
        this.ipAddress = ipAddress;
        this.timestamp = timestamp;
        this.details = details;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(String performedBy) {
        this.performedBy = performedBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public static class Builder {
        private Long id;
        private String action;
        private String entityType;
        private Long entityId;
        private String performedBy;
        private String description;
        private BigDecimal amount;
        private String transactionId;
        private String status;
        private String ipAddress;
        private LocalDateTime timestamp;
        private String details;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder action(String action) {
            this.action = action;
            return this;
        }

        public Builder entityType(String entityType) {
            this.entityType = entityType;
            return this;
        }

        public Builder entityId(Long entityId) {
            this.entityId = entityId;
            return this;
        }

        public Builder performedBy(String performedBy) {
            this.performedBy = performedBy;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder transactionId(String transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder ipAddress(String ipAddress) {
            this.ipAddress = ipAddress;
            return this;
        }

        public Builder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder details(String details) {
            this.details = details;
            return this;
        }

        public AuditLog build() {
            return new AuditLog(id, action, entityType, entityId, performedBy, description,
                    amount, transactionId, status, ipAddress, timestamp, details);
        }
    }
}
