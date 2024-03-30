package com.example.auctionapp.request;

import com.example.auctionapp.entity.CategoryEntity;
import java.util.UUID;

public class CategoryAddRequest {

    private String name;
    private UUID parentCategoryId;

    public CategoryAddRequest() {
    }

    public CategoryEntity toEntity() {
        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setName(this.name);

        // parent category id is handled in services
        return categoryEntity;
    }

    public CategoryAddRequest(String name, UUID parentCategoryId) {
        this.name = name;
        this.parentCategoryId = parentCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(UUID parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }
}