package com.example.auctionapp.request;

import java.time.LocalDate;

public class PaymentAddRequest {
    private String address;
    private String city;
    private String country;
    private String zipCode;
    private String nameOnCard;
    private String cardNumber;
    private LocalDate expirationDate;

    public PaymentAddRequest(final String address,
                             final String city,
                             final String country,
                             final String zipCode, 
                             final String nameOnCard,
                             final String cardNumber,
                             final LocalDate expirationDate) {
        this.address = address;
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
        this.nameOnCard = nameOnCard;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(final String zipCode) {
        this.zipCode = zipCode;
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
}
