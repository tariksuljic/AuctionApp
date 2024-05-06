package com.example.auctionapp.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Bid {
    private UUID id;
    private BigDecimal bidAmount;
    private LocalDateTime bidTime;
    private UUID userId;
    private UUID productId;

    public Bid() {}

    public UUID getId() {
        return this.id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public BigDecimal getBidAmount() {
        return this.bidAmount;
    }

    public void setBidAmount(final BigDecimal bidAmount) {
        this.bidAmount = bidAmount;
    }

    public LocalDateTime getBidTime() {
        return this.bidTime;
    }

    public void setBidTime(final LocalDateTime bidTime) {
        this.bidTime = bidTime;
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
