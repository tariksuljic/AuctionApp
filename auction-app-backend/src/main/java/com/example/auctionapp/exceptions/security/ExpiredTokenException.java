package com.example.auctionapp.exceptions.security;

import com.example.auctionapp.exceptions.GeneralException;
import org.springframework.http.HttpStatus;

public class ExpiredTokenException extends GeneralException {
    public ExpiredTokenException(String message) {
        super(401, message);
    }

    public ExpiredTokenException() {
        super(401);
    }
}
