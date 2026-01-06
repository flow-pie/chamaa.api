package com.chamaa.notifications;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private final EmailService emailService;

    public NotificationService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void notifyContribution(String email, String groupName, String amount) {
        logger.info("Notifying contribution for group: {}", groupName);
        emailService.sendContributionNotification(email, groupName, amount);
    }

    public void notifyLoanApproval(String email, String loanAmount) {
        logger.info("Notifying loan approval for amount: {}", loanAmount);
        emailService.sendLoanApprovalNotification(email, loanAmount);
    }

    public void notifyLoanDisbursement(String email, String loanAmount) {
        logger.info("Notifying loan disbursement for amount: {}", loanAmount);
        emailService.sendLoanDisbursementNotification(email, loanAmount);
    }

    public void notifyRepaymentReminder(String email, String amount, String dueDate) {
        logger.info("Sending repayment reminder to: {}", email);
        emailService.sendRepaymentReminderNotification(email, amount, dueDate);
    }

    public void notifyGroupInvitation(String email, String groupName, String inviteCode) {
        logger.info("Sending group invitation to: {}", email);
        emailService.sendGroupInvitationNotification(email, groupName, inviteCode);
    }

    public void sendCustomNotification(NotificationRequest request) {
        logger.info("Sending custom notification to: {}", request.getRecipientEmail());

        switch (request.getNotificationType().toUpperCase()) {
            case "EMAIL":
                emailService.sendEmail(
                        request.getRecipientEmail(),
                        request.getSubject(),
                        request.getMessage()
                );
                break;
            case "SMS":
                logger.info("SMS notification to: {} - {}", request.getRecipientPhone(), request.getMessage());
                break;
            default:
                logger.warn("Unknown notification type: {}", request.getNotificationType());
        }
    }

    private void sendEmail(String email, String subject, String message) {
        emailService.sendEmail(email, subject, message);
    }
}
