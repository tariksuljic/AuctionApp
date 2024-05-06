package com.example.auctionapp.repository;

import com.example.auctionapp.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface NotificationRepository extends JpaRepository<NotificationEntity, UUID> {
    @Query("SELECT n FROM NotificationEntity n WHERE n.userEntity.userId = :userId " +
            "AND n.productEntity.productId = :productId " +
            "ORDER BY n.notificationTime DESC LIMIT 1")
    Optional<NotificationEntity> findLatestNotificationByUserIdAndProductId(final UUID userId, final UUID productId);
}
