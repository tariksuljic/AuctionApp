package com.example.auctionapp.service;

import com.example.auctionapp.model.PaymentInfo;
import com.example.auctionapp.request.PaymentAddRequest;

import java.util.UUID;

public interface UserService {
    PaymentInfo getPaymentInfoByUser(final UUID userId);
    PaymentInfo addPaymentInfoToUser(final UUID userId, final PaymentAddRequest paymentRequest);
}
