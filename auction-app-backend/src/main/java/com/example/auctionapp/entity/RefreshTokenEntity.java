package com.example.auctionapp.entity;

import com.example.auctionapp.model.RefreshToken;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "refresh_token", schema = "auction_app")
public class RefreshTokenEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "token_id")
    private UUID tokenId;

    @Column(name = "token")
    private String token;

    @Column(name = "expiry_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime expiryDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    public RefreshTokenEntity() { }

    public RefreshTokenEntity(final UUID tokenId,
                              final String token,
                              final LocalDateTime expiryDate,
                              final UserEntity userEntity) {
        this.tokenId = tokenId;
        this.token = token;
        this.expiryDate = expiryDate;
        this.userEntity = userEntity;
    }

    public RefreshToken toDomainModel() {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setTokenId(this.tokenId);
        refreshToken.setToken(this.token);
        refreshToken.setExpiryDate(this.expiryDate);
        refreshToken.setUserEntity(this.userEntity);

        return refreshToken;
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
