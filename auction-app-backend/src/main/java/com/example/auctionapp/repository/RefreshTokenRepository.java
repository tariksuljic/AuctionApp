package com.example.auctionapp.repository;

import com.example.auctionapp.entity.RefreshTokenEntity;
import com.example.auctionapp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, UUID> {
    Optional<RefreshTokenEntity> findRefreshTokenEntityByToken(String token);

    Optional<RefreshTokenEntity> findByUserEntityAndExpiryDateGreaterThan(UserEntity userEntity, LocalDateTime now);
}
