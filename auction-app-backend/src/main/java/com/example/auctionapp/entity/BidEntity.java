package com.example.auctionapp.entity;

import com.example.auctionapp.model.Bid;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "bid", schema = "auction_app")
public class BidEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "bid_id")
    private UUID bidId;

    @Column(name = "bid_amount", precision = 10, scale = 2)
    private BigDecimal bidAmount;

    @Column(name = "bid_time")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime bidTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    public BidEntity(final UUID bidId,
                     final BigDecimal bidAmount,
                     final LocalDateTime bidTime,
                     final UserEntity userEntity,
                     final ProductEntity productEntity) {
        this.bidId = bidId;
        this.bidAmount = bidAmount;
        this.bidTime = bidTime;
        this.userEntity = userEntity;
        this.productEntity = productEntity;
    }

    public BidEntity() { }

    public Bid toDomainModel() {
        Bid bid = new Bid();

        bid.setId(this.bidId);
        bid.setBidAmount(this.bidAmount);
        bid.setBidTime(this.bidTime);
        bid.setUserId(this.userEntity.getUserId());
        bid.setProduct(this.productEntity.toDomainModel());

        return bid;
    }

    public UUID getBidId() {
        return this.bidId;
    }

    public void setBidId(final UUID bidId) {
        this.bidId = bidId;
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

    public UserEntity getUser() {
        return this.userEntity;
    }

    public void setUser(final UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public ProductEntity getProduct() {
        return this.productEntity;
    }

    public void setProduct(final ProductEntity productEntity) {
        this.productEntity = productEntity;
    }
}
