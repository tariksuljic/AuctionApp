package com.example.auctionapp.model;

import java.util.UUID;

public class ProductImage {
    private String imageUrl;
    private UUID productId;

    public ProductImage() {
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public UUID getProductId() {
        return this.productId;
    }

    public void setProductId(final UUID productId) {
        this.productId = productId;
    }
}
