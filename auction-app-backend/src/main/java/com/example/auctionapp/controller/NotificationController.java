package com.example.auctionapp.controller;

import com.example.auctionapp.response.NotificationResponse;
import com.example.auctionapp.service.NotificationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(final NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/latest")
    public NotificationResponse getLatestNotification(
            @RequestParam(value = "userId") final UUID userId,
            @RequestParam(value = "productId") final UUID productId) {
        return notificationService.getLatestNotificationForUserAndProduct(userId, productId);
    }
}
