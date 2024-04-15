package com.example.auctionapp.response;

import com.example.auctionapp.model.Product;
import org.springframework.data.domain.Page;

public class ProductSearchResponse {
    private Page<Product> products;
    private String suggestion;

    public ProductSearchResponse(final Page<Product> products, final String suggestion) {
        this.products = products;
        this.suggestion = suggestion;
    }

    public Page<Product> getProducts() {
        return products;
    }

    public void setProducts(final Page<Product> products) {
        this.products = products;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(final String suggestion) {
        this.suggestion = suggestion;
    }
}
