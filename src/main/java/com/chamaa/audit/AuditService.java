package com.chamaa.audit;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class AuditService {

    private static final Logger logger = LoggerFactory.getLogger(AuditService.class);

    public void logTransaction(String action, String entityType, Long entityId, 
                               String performedBy, BigDecimal amount, String transactionId) {
        AuditLog auditLog = AuditLog.builder()
                .action(action)
                .entityType(entityType)
                .entityId(entityId)
                .performedBy(performedBy)
                .amount(amount)
                .transactionId(transactionId)
                .status("COMPLETED")
                .timestamp(LocalDateTime.now())
                .build();

        logAudit(auditLog);
    }

    public void logLoanApplication(Long loanId, String userId, BigDecimal amount, String status) {
        AuditLog auditLog = AuditLog.builder()
                .action("LOAN_APPLICATION")
                .entityType("LOAN")
                .entityId(loanId)
                .performedBy(userId)
                .amount(amount)
                .status(status)
                .timestamp(LocalDateTime.now())
                .description(String.format("Loan application for amount: %s with status: %s", amount, status))
                .build();

        logAudit(auditLog);
    }

    public void logLoanApproval(Long loanId, String approvedBy, BigDecimal amount) {
        AuditLog auditLog = AuditLog.builder()
                .action("LOAN_APPROVAL")
                .entityType("LOAN")
                .entityId(loanId)
                .performedBy(approvedBy)
                .amount(amount)
                .status("APPROVED")
                .timestamp(LocalDateTime.now())
                .description(String.format("Loan approved for amount: %s", amount))
                .build();

        logAudit(auditLog);
    }

    public void logContribution(Long groupId, String userId, BigDecimal amount, String transactionId) {
        AuditLog auditLog = AuditLog.builder()
                .action("CONTRIBUTION")
                .entityType("GROUP")
                .entityId(groupId)
                .performedBy(userId)
                .amount(amount)
                .transactionId(transactionId)
                .status("COMPLETED")
                .timestamp(LocalDateTime.now())
                .description(String.format("Contribution of %s to group %d", amount, groupId))
                .build();

        logAudit(auditLog);
    }

    public void logWithdrawal(Long walletId, String userId, BigDecimal amount, String transactionId) {
        AuditLog auditLog = AuditLog.builder()
                .action("WITHDRAWAL")
                .entityType("WALLET")
                .entityId(walletId)
                .performedBy(userId)
                .amount(amount)
                .transactionId(transactionId)
                .status("COMPLETED")
                .timestamp(LocalDateTime.now())
                .description(String.format("Withdrawal of %s from wallet", amount))
                .build();

        logAudit(auditLog);
    }

    public void logSecurityEvent(String action, String userId, String description) {
        AuditLog auditLog = AuditLog.builder()
                .action(action)
                .entityType("USER")
                .performedBy(userId)
                .status("LOGGED")
                .timestamp(LocalDateTime.now())
                .description(description)
                .build();

        logAudit(auditLog);
    }

    private void logAudit(AuditLog auditLog) {
        logger.info("Audit Log - Action: {}, Entity: {}, User: {}, Amount: {}, Timestamp: {}",
                auditLog.getAction(),
                auditLog.getEntityType(),
                auditLog.getPerformedBy(),
                auditLog.getAmount(),
                auditLog.getTimestamp());

        if (auditLog.getDescription() != null) {
            logger.debug("Audit Details: {}", auditLog.getDescription());
        }
    }
}
