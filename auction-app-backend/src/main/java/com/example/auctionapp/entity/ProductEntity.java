package com.example.auctionapp.entity;

import com.example.auctionapp.model.Product;
import com.example.auctionapp.entity.CategoryEntity;
import com.example.auctionapp.entity.ProductImageEntity;
import com.example.auctionapp.model.ProductImage;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Entity
@Table(name = "product", schema="auction_app")
public class ProductEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "product_id")
    private UUID productId;

    @Column(name = "name")
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "start_price", precision = 10, scale = 2)
    private BigDecimal startPrice;

    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime endDate;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryEntity;

    @OneToMany(mappedBy = "productEntity", fetch = FetchType.LAZY)
    private List<ProductImageEntity> productImages;

    public ProductEntity() {
    }

    public ProductEntity(final UUID productId,
                         final String name,
                         final String description,
                         final BigDecimal startPrice,
                         final LocalDateTime startDate,
                         final LocalDateTime endDate,
                         final String status,
                         final CategoryEntity categoryEntity) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.startPrice = startPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.categoryEntity = categoryEntity;
    }

    public Product toDomainModel() {
        Product product = new Product();

        product.setId(this.productId);
        product.setName(this.name);
        product.setDescription(this.description);
        product.setStartPrice(this.startPrice);
        product.setStartDate(this.startDate);
        product.setEndDate(this.endDate);
        product.setStatus(this.status);
        product.setCategoryId(this.categoryEntity.getCategoryId());
        List<ProductImage> productImageList = this.productImages.stream()
                .map(ProductImageEntity::toDomainModel)
                .collect(toList());
        product.setProductImages(productImageList);

        return product;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(final UUID productId) {
        this.productId = productId;
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

    public CategoryEntity getCategory() {
        return categoryEntity;
    }

    public void setCategory(final CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }

    public List<ProductImageEntity> getProductImages() {
        return productImages;
    }

    public void setProductImages(final List<ProductImageEntity> productImages) {
        this.productImages = productImages;
    }
}
