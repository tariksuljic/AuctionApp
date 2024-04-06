package com.example.auctionapp.model;

import java.util.UUID;
import java.util.List;

public class Category {
    private UUID id;
    private String name;
    private Category parentCategory;
    private List<Category> subCategories;
    private int productCount;

    public Category() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(final Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public List<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(final List<Category> subCategories) {
        this.subCategories = subCategories;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(final int productCount) {
        this.productCount = productCount;
    }
}
