package com.example.auctionapp.entity;

import com.example.auctionapp.model.ProductImage;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "product_image", schema="auction_app")
public class ProductImageEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "image_id")
    private UUID imageId;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    public ProductImageEntity() {
    }

    public ProductImageEntity(final UUID imageId, final String imageUrl, final ProductEntity productEntity) {
        this.imageId = imageId;
        this.imageUrl = imageUrl;
        this.productEntity = productEntity;
    }

    public ProductImage toDomainModel() {
        ProductImage productImage = new ProductImage();

        productImage.setImageUrl(this.imageUrl);
        productImage.setProductId(this.productEntity.getProductId());

        return productImage;
    }

    public UUID getImageId() {
        return imageId;
    }

    public void setImageId(final UUID imageId) {
        this.imageId = imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(final ProductEntity productEntity) {
        this.productEntity = productEntity;
    }
}
