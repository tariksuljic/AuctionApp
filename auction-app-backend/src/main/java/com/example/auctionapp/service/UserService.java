package com.example.auctionapp.service;

import com.example.auctionapp.model.User;

import java.util.UUID;

public interface UserService {
    User getUser(final UUID userId);
}
