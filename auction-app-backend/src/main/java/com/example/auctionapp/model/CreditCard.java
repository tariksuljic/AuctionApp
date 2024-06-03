package com.example.auctionapp.model;

import java.time.LocalDate;
import java.util.UUID;

public class CreditCard {
    private UUID creditCardId;
    private String nameOnCard;
    private String cardNumber;
    private LocalDate expirationDate;
    private String stripeToken;

    public CreditCard() {
    }

    public UUID getCreditCardId() {
        return this.creditCardId;
    }

    public void setCreditCardId(final UUID creditCardId) {
        this.creditCardId = creditCardId;
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

    public String getStripeToken() {
        return this.stripeToken;
    }

    public void setStripeToken(final String stripeToken) {
        this.stripeToken = stripeToken;
    }
}
