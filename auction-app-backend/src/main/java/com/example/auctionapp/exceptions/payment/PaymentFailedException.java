package com.example.auctionapp.exceptions.payment;

import com.example.auctionapp.exceptions.GeneralException;
import org.springframework.http.HttpStatus;

public class PaymentFailedException extends GeneralException {
    public PaymentFailedException(String message) {
        super(400, message);
    }

    public PaymentFailedException() {
        super(400);
    }
}
