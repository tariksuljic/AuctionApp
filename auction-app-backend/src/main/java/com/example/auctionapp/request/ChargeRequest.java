package com.example.auctionapp.request;

import com.example.auctionapp.model.Product;

import java.util.Currency;

public class ChargeRequest {
    private String customerName;
    private String customerEmail;
    private Product product;

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(final String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return this.customerEmail;
    }

    public void setCustomerEmail(final String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(final Product product) {
        this.product = product;
    }
}
