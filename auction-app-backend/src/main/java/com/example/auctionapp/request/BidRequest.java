package com.example.auctionapp.request;

import com.example.auctionapp.entity.BidEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class BidRequest {
    private BigDecimal bidAmount;
    private LocalDateTime bidTime;
    private UUID productId;
    private UUID userId;

    public BidRequest() {
    }

    public BidRequest(final BigDecimal bidAmount,
                      final LocalDateTime bidTime,
                      final UUID productId,
                      final UUID userId) {
        this.bidAmount = bidAmount;
        this.bidTime = bidTime;
        this.productId = productId;
        this.userId = userId;
    }

    public BidEntity toEntity() {
        BidEntity bidEntity = new BidEntity();

        bidEntity.setBidAmount(this.bidAmount);
        bidEntity.setBidTime(this.bidTime);

        return bidEntity;
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

    public UUID getProductId() {
        return this.productId;
    }

    public void setProductId(final UUID productId) {
        this.productId = productId;
    }

    public UUID getUserId() {
        return this.userId;
    }

    public void setUserId(final UUID userId) {
        this.userId = userId;
    }
}
