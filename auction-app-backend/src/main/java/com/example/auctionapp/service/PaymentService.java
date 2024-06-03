package com.example.auctionapp.service;

import com.example.auctionapp.model.PaymentInfo;
import com.example.auctionapp.request.PaymentAddRequest;
import com.example.auctionapp.request.StripePaymentAddRequest;

public interface PaymentService {
    PaymentInfo addStripePaymentInfo(final StripePaymentAddRequest stripePaymentAddRequest);
    PaymentInfo addNewPaymentInfo(final PaymentAddRequest paymentAddRequest);
}
