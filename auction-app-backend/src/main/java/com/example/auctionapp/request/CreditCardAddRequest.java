package com.example.auctionapp.request;

import com.example.auctionapp.entity.CreditCardEntity;
import com.example.auctionapp.util.annotation.LuhnCheck;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class CreditCardAddRequest {
    @NotEmpty(message = "Name on card is required")
    @Size(min = 2, message = "Name on card must be at least 2 characters long")
    private String nameOnCard;

    @LuhnCheck
    @Size(min = 14, message = "Credit card number should be a valid 14-digit number")
    @NotEmpty(message = "Card number is required")
    private String cardNumber;

    @Future(message = "Expiration date must be in the future")
    private LocalDate expirationDate;

    public CreditCardAddRequest() {
    }

    public CreditCardEntity toEntity() {
        CreditCardEntity creditCardEntity = new CreditCardEntity();

        creditCardEntity.setNameOnCard(this.nameOnCard);
        creditCardEntity.setCardNumber(this.cardNumber);
        creditCardEntity.setExpirationDate(this.expirationDate);

        return creditCardEntity;
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
