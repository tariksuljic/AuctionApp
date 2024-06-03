package com.example.auctionapp.model;

import com.example.auctionapp.entity.enums.ProductStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;

public class Product {
    private UUID id;
    private String name;
    private String description;
    private BigDecimal startPrice;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private ProductStatus status;
    private UUID categoryId;
    List<ProductImage> productImages;
    private UUID userId;
    private int bidsCount;
    private BigDecimal highestBid;
    private UUID highestBidderId;

    public Product() {
    }

    public Product(final UUID id,
                   final String name,
                   final String description,
                   final BigDecimal startPrice,
                   final LocalDateTime startDate,
                   final LocalDateTime endDate,
                   final ProductStatus status,
                   final UUID categoryId,
                   final List<ProductImage> productImages,
                   final UUID userId,
                   final int bidsCount,
                   final BigDecimal highestBid) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startPrice = startPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.categoryId = categoryId;
        this.productImages = productImages;
        this.userId = userId;
        this.bidsCount = bidsCount;
        this.highestBid = highestBid;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public BigDecimal getStartPrice() {
        return this.startPrice;
    }

    public void setStartPrice(final BigDecimal startPrice) {
        this.startPrice = startPrice;
    }

    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    public void setStartDate(final LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    public void setEndDate(final LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public ProductStatus getStatus() {
        return this.status;
    }

    public void setStatus(final ProductStatus status) {
        this.status = status;
    }

    public UUID getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(final UUID categoryId) {
        this.categoryId = categoryId;
    }

    public List<ProductImage> getProductImages() {
        return this.productImages;
    }

    public void setProductImages(final List<ProductImage> productImages) {
        this.productImages = productImages;
    }

    public UUID getUserId() {
        return this.userId;
    }

    public void setUserId(final UUID userId) {
        this.userId = userId;
    }

    public int getBidsCount() {
        return this.bidsCount;
    }

    public void setBidsCount(final int bidsCount) {
        this.bidsCount = bidsCount;
    }

    public BigDecimal getHighestBid() {
        return this.highestBid;
    }

    public void setHighestBid(final BigDecimal highestBid) {
        this.highestBid = highestBid;
    }

    public UUID getHighestBidderId() {
        return this.highestBidderId;
    }

    public void setHighestBidderId(final UUID highestBidderId) {
        this.highestBidderId = highestBidderId;
    }
}
