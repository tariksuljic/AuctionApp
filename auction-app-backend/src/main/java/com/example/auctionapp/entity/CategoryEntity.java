package com.example.auctionapp.entity;

import com.example.auctionapp.model.Category;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "category", schema="auction_app")
public class CategoryEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "category_id")
    private UUID categoryId;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id")
    private CategoryEntity parentCategory;

    public CategoryEntity() {
    }

    public CategoryEntity(final UUID categoryId, final String name, final CategoryEntity parentCategory) {
        this.categoryId = categoryId;
        this.name = name;
        this.parentCategory = parentCategory;
    }

    public Category toDomainModel() {
        Category category = new Category();

        category.setId(this.categoryId);
        category.setName(this.name);

        if (this.parentCategory != null) {
            category.setParentCategory(this.parentCategory.toDomainModel());
        }

        return category;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(final UUID categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public CategoryEntity getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(final CategoryEntity parentCategory) {
        this.parentCategory = parentCategory;
    }
}
