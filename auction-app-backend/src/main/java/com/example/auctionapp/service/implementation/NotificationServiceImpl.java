package com.example.auctionapp.service.implementation;

import com.example.auctionapp.entity.NotificationEntity;
import com.example.auctionapp.entity.ProductEntity;
import com.example.auctionapp.entity.UserEntity;
import com.example.auctionapp.entity.enums.NotificationType;
import com.example.auctionapp.exceptions.repository.ResourceNotFoundException;
import com.example.auctionapp.repository.NotificationRepository;
import com.example.auctionapp.repository.ProductRepository;
import com.example.auctionapp.repository.UserRepository;
import com.example.auctionapp.response.NotificationResponse;
import com.example.auctionapp.service.NotificationService;
import com.example.auctionapp.websockets.MainSocketHandler;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final MainSocketHandler mainSocketHandler;
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public NotificationServiceImpl(
            final MainSocketHandler mainSocketHandler,
            final NotificationRepository notificationRepository,
            final UserRepository userRepository,
            final ProductRepository productRepository) {
        this.mainSocketHandler = mainSocketHandler;
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void notifyUser(final UUID userId, final NotificationType notificationType, final UUID productId) {
        NotificationEntity notificationEntity = new NotificationEntity();

        notificationEntity.setNotificationType(notificationType);
        notificationEntity.setNotificationTime(LocalDateTime.now());

        final UserEntity userEntity = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User with the given ID does not exist"));

        notificationEntity.setUser(userEntity);

        final ProductEntity productEntity = productRepository.findById(productId).orElseThrow(() ->
                new ResourceNotFoundException("Product with the given ID does not exist"));

        notificationEntity.setProductEntity(productEntity);

        this.notificationRepository.save(notificationEntity);
        this.mainSocketHandler.sendMessage(String.valueOf(userId), notificationType.label);
    }

    @Override
    public NotificationResponse getLatestNotificationForUserAndProduct(final UUID userId, final UUID productId) {
        final NotificationEntity notificationEntity =  notificationRepository.findLatestNotificationByUserIdAndProductId(userId, productId)
                .orElseThrow(() -> new ResourceNotFoundException("No notifications found for the given user and product IDs"));

        return new NotificationResponse(notificationEntity.getNotificationType());
    }
}
