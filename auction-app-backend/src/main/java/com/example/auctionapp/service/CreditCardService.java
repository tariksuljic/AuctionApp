package com.example.auctionapp.service;

import com.example.auctionapp.model.CreditCard;
import com.example.auctionapp.request.CreditCardAddRequest;

public interface CreditCardService {
    public CreditCard addNewCreditCard(final CreditCardAddRequest creditCardAddRequest);
}
