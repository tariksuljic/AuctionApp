package com.example.auctionapp.request;

import com.example.auctionapp.entity.enums.NotificationType;

public class NotificationRequest {
    private NotificationType notificationType;

    public NotificationRequest() {}

    public NotificationRequest(final NotificationType message) {
        this.notificationType = message;
    }

    public NotificationType getNotificationType() {
        return this.notificationType;
    }

    public void setNotificationType(final NotificationType notificationType) {
        this.notificationType = notificationType;
    }
}
