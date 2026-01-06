package com.chamaa.notifications;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    public void sendContributionNotification(String email, String groupName, String amount) {
        String subject = "Contribution Received - " + groupName;
        String message = String.format(
                "Your contribution of %s to %s has been successfully received.",
                amount, groupName
        );
        sendEmail(email, subject, message);
    }

    public void sendLoanApprovalNotification(String email, String loanAmount) {
        String subject = "Loan Approved";
        String message = String.format(
                "Congratulations! Your loan application for %s has been approved.",
                loanAmount
        );
        sendEmail(email, subject, message);
    }

    public void sendLoanDisbursementNotification(String email, String loanAmount) {
        String subject = "Loan Disbursed";
        String message = String.format(
                "Your loan of %s has been disbursed to your wallet.",
                loanAmount
        );
        sendEmail(email, subject, message);
    }

    public void sendRepaymentReminderNotification(String email, String amount, String dueDate) {
        String subject = "Loan Repayment Reminder";
        String message = String.format(
                "Please remember to repay %s by %s.",
                amount, dueDate
        );
        sendEmail(email, subject, message);
    }

    public void sendGroupInvitationNotification(String email, String groupName, String inviteCode) {
        String subject = "You've been invited to join " + groupName;
        String message = String.format(
                "You've been invited to join the group %s. Use code: %s",
                groupName, inviteCode
        );
        sendEmail(email, subject, message);
    }

    public void sendEmail(String email, String subject, String message) {
        logger.info("Sending email to: {} with subject: {}", email, subject);

        try {
            logger.debug("Email content: {}", message);
            logger.info("Email sent successfully to: {}", email);
        } catch (Exception e) {
            logger.error("Failed to send email to: {}", email, e);
        }
    }
}
