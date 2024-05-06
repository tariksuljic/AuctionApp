package com.example.auctionapp.response;

import com.example.auctionapp.entity.enums.NotificationType;

public class NotificationResponse {
    private NotificationType notificationType;
    private String messageContent;

    public NotificationResponse(final NotificationType notificationType) {
        this.notificationType = notificationType;
        this.messageContent = notificationType.label;
    }

    public NotificationType getMessage() {
        return this.notificationType;
    }

    public void setMessage(final NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public NotificationType getNotificationType() {
        return this.notificationType;
    }

    public void setNotificationType(final NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public String getMessageContent() {
        return this.messageContent;
    }

    public void setMessageContent(final String messageContent) {
        this.messageContent = messageContent;
    }
}
