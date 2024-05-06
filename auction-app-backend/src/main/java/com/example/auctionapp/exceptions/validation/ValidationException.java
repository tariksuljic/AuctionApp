package com.example.auctionapp.exceptions.validation;

import com.example.auctionapp.exceptions.GeneralException;

public class ValidationException extends GeneralException {
    public ValidationException(String message) {
        super(400, message);
    }

    public ValidationException() {
        super(400);
    }
}
