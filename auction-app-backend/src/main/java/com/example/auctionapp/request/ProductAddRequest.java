package com.example.auctionapp.request;

import com.example.auctionapp.entity.ProductEntity;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class ProductAddRequest {
    @NotEmpty(message = "Product name is required")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters long")
    private String name;

    @NotEmpty(message = "Description is required")
    @Size(min = 10, max = 700, message = "Description must be between 10 and 700 characters long")
    private String description;

    @NotNull(message = "Start price is required")
    @DecimalMin(value = "0.01", message = "Start price must be at least 0.01")
    private BigDecimal startPrice;

    @FutureOrPresent(message = "Start date must be in the present or future")
    private LocalDateTime startDate;

    @Future(message = "End date must be in the future")
    private LocalDateTime endDate;

    @NotNull(message = "Category ID is required")
    private UUID categoryId;

    @NotNull(message = "User ID is required")
    private UUID userId;

    @NotEmpty(message = "Name on card is required")
    @Size(min = 2, message = "Name on card must be at least 2 characters long")
    private String nameOnCard;

    @NotEmpty(message = "Card number is required")
    private String cardNumber;

    @NotEmpty(message = "Address is required")
    @Pattern(regexp = "^[a-zA-Z0-9\\s,.'-]{3,100}$", message = "Invalid address")
    private String address;

    @NotEmpty(message = "City is required")
    @Pattern(regexp = "^[a-zA-Z\\s]{3,50}$", message = "Invalid city")
    private String city;

    @NotEmpty(message = "Zip code is required")
    @Pattern(regexp = "^[0-9]{5,10}$", message = "Invalid zip code")
    private String zipCode;

    @NotEmpty(message = "Country is required")
    @Pattern(regexp = "^[a-zA-Z\\s]{3,50}$", message = "Invalid country")
    private String country;

    @Future(message = "Expiration date must be in the future")
    private LocalDate expirationDate;

    private boolean dataChanged;

    public ProductAddRequest() {
    }

    public ProductEntity toEntity() {
        ProductEntity productEntity = new ProductEntity();

        productEntity.setName(this.name);
        productEntity.setDescription(this.description);
        productEntity.setStartPrice(this.startPrice);
        productEntity.setStartDate(this.startDate);
        productEntity.setEndDate(this.endDate);

        return productEntity;
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

    public UUID getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(final UUID categoryId) {
        this.categoryId = categoryId;
    }

    public UUID getUserId() {
        return this.userId;
    }

    public void setUserId(final UUID userId) {
        this.userId = userId;
    }

    public String getNameOnCard() {
        return this.nameOnCard;
    }

    public void setNameOnCard(final String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public void setCardNumber(final String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(final LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(final String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public boolean isDataChanged() {
        return dataChanged;
    }

    public void setDataChanged(boolean dataChanged) {
        this.dataChanged = dataChanged;
    }
}
