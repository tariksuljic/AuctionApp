package com.example.auctionapp.exceptions.authentication;

import com.example.auctionapp.exceptions.GeneralException;

public class EmailAlreadyInUseException extends GeneralException {
    public EmailAlreadyInUseException(String message) {
        super(400, message);
    }

    public EmailAlreadyInUseException() {
        super(400);
    }
}
