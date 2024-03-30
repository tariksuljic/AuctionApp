package com.example.auctionapp.request;

import com.example.auctionapp.entity.ProductEntity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class ProductAddRequest {

    private String name;
    private String description;
    private BigDecimal startPrice;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String imageUrl;
    private String status;
    private UUID categoryId;

    public ProductAddRequest() {
    }

    public ProductEntity toEntity() {
        ProductEntity productEntity = new ProductEntity();

        productEntity.setName(this.name);
        productEntity.setDescription(this.description);
        productEntity.setStartPrice(this.startPrice);
        productEntity.setStartDate(this.startDate);
        productEntity.setEndDate(this.endDate);
        productEntity.setImageUrl(this.imageUrl);
        productEntity.setStatus(this.status);

        // category id is set in services
        return productEntity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(BigDecimal startPrice) {
        this.startPrice = startPrice;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }
}
