package com.example.auctionapp.model;

import com.example.auctionapp.entity.enums.NotificationType;

import java.time.LocalDateTime;
import java.util.UUID;

public class Notification {
    private UUID notificationId;
    private NotificationType notificationType;
    private LocalDateTime notificationTime;
    private UUID userId;
    private UUID productId;

    public Notification() { }

    public UUID getNotificationId() {
        return this.notificationId;
    }

    public void setNotificationId(final UUID notificationId) {
        this.notificationId = notificationId;
    }

    public NotificationType getNotificationType() {
        return this.notificationType;
    }

    public void setNotificationType(final NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public LocalDateTime getNotificationTime() {
        return this.notificationTime;
    }

    public void setNotificationTime(final LocalDateTime notificationTime) {
        this.notificationTime = notificationTime;
    }

    public UUID getUserId() {
        return this.userId;
    }

    public void setUserId(final UUID userId) {
        this.userId = userId;
    }

    public UUID getProductId() {
        return this.productId;
    }

    public void setProductId(final UUID productId) {
        this.productId = productId;
    }
}
