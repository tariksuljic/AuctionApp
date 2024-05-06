package com.example.auctionapp.entity;

import com.example.auctionapp.model.Product;
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
import org.hibernate.annotations.Formula;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private UserEntity userEntity;

    @OneToMany(mappedBy = "productEntity", fetch = FetchType.LAZY)
    private List<ProductImageEntity> productImages;

    @Formula("(SELECT COUNT(*) FROM auction_app.bid b WHERE b.product_id = product_id)")
    private int bidsCount;

    @Formula("(SELECT MAX(b.bid_amount) FROM auction_app.bid b WHERE b.product_id = product_id)")
    private BigDecimal highestBid;

    public ProductEntity() {
    }

    public ProductEntity(final UUID productId,
                         final String name,
                         final String description,
                         final BigDecimal startPrice,
                         final LocalDateTime startDate,
                         final LocalDateTime endDate,
                         final String status,
                         final CategoryEntity categoryEntity,
                         final UserEntity userEntity) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.startPrice = startPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.categoryEntity = categoryEntity;
        this.userEntity = userEntity;
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
        product.setUserId(this.userEntity.getUserId());
        product.setBidsCount(this.bidsCount);
        product.setHighestBid(this.highestBid);

        return product;
    }

    public UUID getProductId() {
        return this.productId;
    }

    public void setProductId(final UUID productId) {
        this.productId = productId;
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

    public String getStatus() {
        return this.status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public CategoryEntity getCategory() {
        return this.categoryEntity;
    }

    public void setCategory(final CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }

    public List<ProductImageEntity> getProductImages() {
        return this.productImages;
    }

    public void setProductImages(final List<ProductImageEntity> productImages) {
        this.productImages = productImages;
    }

    public CategoryEntity getCategoryEntity() {
        return this.categoryEntity;
    }

    public void setCategoryEntity(final CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }

    public UserEntity getUserEntity() {
        return this.userEntity;
    }

    public void setUserEntity(final UserEntity userEntity) {
        this.userEntity = userEntity;
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
}
