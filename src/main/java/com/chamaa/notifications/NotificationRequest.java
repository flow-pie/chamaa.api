package com.chamaa.notifications;

public class NotificationRequest {
    private String recipientEmail;
    private String recipientPhone;
    private String subject;
    private String message;
    private String notificationType;
    private Long referenceId;

    public NotificationRequest() {
    }

    public NotificationRequest(String recipientEmail, String recipientPhone, String subject,
                              String message, String notificationType, Long referenceId) {
        this.recipientEmail = recipientEmail;
        this.recipientPhone = recipientPhone;
        this.subject = subject;
        this.message = message;
        this.notificationType = notificationType;
        this.referenceId = referenceId;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public String getRecipientPhone() {
        return recipientPhone;
    }

    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public Long getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
    }
}
