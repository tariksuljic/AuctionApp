package com.example.auctionapp.controller;

import com.example.auctionapp.exceptions.security.RefreshTokenNotFoundException;
import com.example.auctionapp.model.User;
import com.example.auctionapp.request.LoginRequest;
import com.example.auctionapp.request.UserRequest;
import com.example.auctionapp.response.JwtResponse;
import com.example.auctionapp.service.AuthService;
import com.example.auctionapp.util.CookieUtility;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/auth")
@SecurityRequirement(name = "JWT Security")
public class AuthController {
    private final AuthService authService;

    @Value("${JWT_SECURE}")
    private boolean jwtSecure;

    @Value("${cookie.accessExpiry}")
    private int accessExpiry;

    @Value("${cookie.refreshExpiry}")
    private int refreshExpiry;

    public AuthController(final AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public User register(@RequestBody final UserRequest user) {
        return authService.signUp(user);
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody final LoginRequest loginRequest, HttpServletResponse response) {
        final JwtResponse jwtResponse = authService.signIn(loginRequest);

        CookieUtility.addCookie(response, CookieUtility.accessToken, jwtResponse.getAccessToken(), jwtSecure, accessExpiry);
        CookieUtility.addCookie(response, CookieUtility.refreshToken, jwtResponse.getRefreshToken(), jwtSecure, refreshExpiry);

        return jwtResponse;
    }

    @PostMapping("/refresh-token")
    public String refreshToken(HttpServletRequest request, HttpServletResponse response) {
        final String refreshToken = CookieUtility.extractCookieValue(request, CookieUtility.refreshToken);

        if(refreshToken == null) {
            throw new RefreshTokenNotFoundException("No refresh token found in request");
        }

        final String newAccessToken = authService.refreshAccessToken(refreshToken);

        CookieUtility.addCookie(response, CookieUtility.accessToken, newAccessToken, jwtSecure, accessExpiry);

        return newAccessToken;
    }

    @GetMapping("/logout")
    public void logout(HttpServletResponse response, HttpServletRequest request) {
        final String refreshToken = CookieUtility.extractCookieValue(request, CookieUtility.refreshToken);
        
        if (refreshToken == null) {
            throw new RefreshTokenNotFoundException("No refresh token found on logout");
        }

        authService.deleteRefreshToken(refreshToken);

        CookieUtility.deleteCookie(response, CookieUtility.accessToken, jwtSecure);
        CookieUtility.deleteCookie(response, CookieUtility.refreshToken, jwtSecure);
    }
}
