package com.example.auctionapp.request;

import java.util.UUID;

public class GetProductRequest {
    private UUID categoryId;
    private String searchProduct;
    private String sortField = "name";
    private String sortDirection = "ASC";
    private int page = 0;
    private int size = 8;

    public GetProductRequest() {
    }

    public GetProductRequest(final UUID categoryId,
                             final String searchProduct,
                             final String sortField,
                             final String sortDirection,
                             final int page,
                             final int size) {
        this.categoryId = categoryId;
        this.searchProduct = searchProduct;
        this.sortField = sortField;
        this.sortDirection = sortDirection;
        this.page = page;
        this.size = size;
    }

    public UUID getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(final UUID categoryId) {
        this.categoryId = categoryId;
    }

    public String getSearchProduct() {
        return this.searchProduct;
    }

    public void setSearchProduct(final String searchProduct) {
        this.searchProduct = searchProduct;
    }

    public String getSortField() {
        return this.sortField;
    }

    public void setSortField(final String sortField) {
        this.sortField = sortField;
    }

    public String getSortDirection() {
        return this.sortDirection;
    }

    public void setSortDirection(final String sortDirection) {
        this.sortDirection = sortDirection;
    }

    public int getPage() {
        return this.page;
    }

    public void setPage(final int page) {
        this.page = page;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(final int size) {
        this.size = size;
    }
}
