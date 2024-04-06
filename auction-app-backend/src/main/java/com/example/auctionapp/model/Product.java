package com.example.auctionapp.model;

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
    private String status;
    private UUID categoryId;
    List<ProductImage> productImages;

    public Product() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public BigDecimal getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(final BigDecimal startPrice) {
        this.startPrice = startPrice;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(final LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(final LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(final UUID categoryId) {
        this.categoryId = categoryId;
    }

    public List<ProductImage> getProductImages() {
        return productImages;
    }

    public void setProductImages(final List<ProductImage> productImages) {
        this.productImages = productImages;
    }
}
