package com.example.auctionapp.entity;

import com.example.auctionapp.model.Category;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;
import java.util.List;

import static java.util.stream.Collectors.toList;

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

    @OneToMany(mappedBy = "parentCategory", fetch = FetchType.LAZY)
    private List<CategoryEntity> subCategories;

    @Formula("(SELECT COUNT(*) FROM auction_app.product p WHERE p.category_id = category_id)")
    private int productCount;

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
        category.setProductCount(this.productCount);

        if (this.subCategories != null && !this.subCategories.isEmpty()) {
            List<Category> subCategoryModels = this.subCategories.stream()
                    .map(CategoryEntity::toDomainModel)
                    .collect(toList());
            category.setSubCategories(subCategoryModels);
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

    public List<CategoryEntity> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(final List<CategoryEntity> subCategories) {
        this.subCategories = subCategories;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(final int productCount) {
        this.productCount = productCount;
    }
}
