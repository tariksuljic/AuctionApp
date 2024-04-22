package com.example.auctionapp.service;

import com.example.auctionapp.model.User;
import com.example.auctionapp.request.LoginRequest;
import com.example.auctionapp.request.UserRequest;
import com.example.auctionapp.response.JwtResponse;

public interface AuthService {
    User signUp(final UserRequest userRequest);
    JwtResponse signIn(final LoginRequest loginRequest);
    String refreshAccessToken(final String token);
    void deleteRefreshToken(final String token);
}
