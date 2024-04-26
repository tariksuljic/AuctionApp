package com.example.auctionapp.response;

public class JwtResponse {
    private String name;
    private String accessToken;
    private String refreshToken;

    public JwtResponse(final String name, final String accessToken, final String refreshToken) {
        this.name = name;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(final String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public void setRefreshToken(final String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
