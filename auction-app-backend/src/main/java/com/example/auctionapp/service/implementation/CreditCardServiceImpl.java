package com.example.auctionapp.service.implementation;

import com.example.auctionapp.model.CreditCard;
import com.example.auctionapp.repository.CreditCardRepository;
import com.example.auctionapp.request.CreditCardAddRequest;
import com.example.auctionapp.service.CreditCardService;

public class CreditCardServiceImpl implements CreditCardService {
    private final CreditCardRepository creditCardRepository;

    public CreditCardServiceImpl(final CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    @Override
    public CreditCard addNewCreditCard(final CreditCardAddRequest creditCardAddRequest) {
        return creditCardRepository.save(creditCardAddRequest.toEntity()).toDomainModel();
    }
}
