package com.example.auctionapp.model;

import com.example.auctionapp.entity.RefreshTokenEntity;
import com.example.auctionapp.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class RefreshToken {
    private UUID tokenId;
    private String token;
    private LocalDateTime expiryDate;
    private UserEntity userEntity;

    public RefreshTokenEntity toEntity() {
        RefreshTokenEntity entity = new RefreshTokenEntity();

        entity.setTokenId(this.tokenId);
        entity.setToken(this.token);
        entity.setExpiryDate(this.expiryDate);
        entity.setUserEntity(this.userEntity);

        return entity;
    }

    public UUID getTokenId() {
        return this.tokenId;
    }

    public void setTokenId(final UUID tokenId) {
        this.tokenId = tokenId;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(final String token) {
        this.token = token;
    }

    public LocalDateTime getExpiryDate() {
        return this.expiryDate;
    }

    public void setExpiryDate(final LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public UserEntity getUserEntity() {
        return this.userEntity;
    }

    public void setUserEntity(final UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
