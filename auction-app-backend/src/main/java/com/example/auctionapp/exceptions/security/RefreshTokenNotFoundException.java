package com.example.auctionapp.exceptions.security;

import com.example.auctionapp.exceptions.GeneralException;

public class RefreshTokenNotFoundException  extends GeneralException {
    public RefreshTokenNotFoundException(String message) {
        super(401, message);
    }

    public RefreshTokenNotFoundException() {
        super(401);
    }
}
