package com.example.auctionapp.response;

public class PaymentResponse {
    private String clientSecret;

    public PaymentResponse(final String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public void setClientSecret(final String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getClientSecret() {
        return this.clientSecret;
    }
}
