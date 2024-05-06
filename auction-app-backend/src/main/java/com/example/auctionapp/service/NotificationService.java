package com.example.auctionapp.service;

import com.example.auctionapp.entity.enums.NotificationType;
import com.example.auctionapp.response.NotificationResponse;

import java.util.UUID;

public interface NotificationService {
    void notifyUser(final UUID userId, final NotificationType notificationType, final UUID productId);
    NotificationResponse getLatestNotificationForUserAndProduct(final UUID userId, final UUID productId);
}
