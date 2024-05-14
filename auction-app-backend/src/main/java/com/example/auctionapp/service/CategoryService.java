package com.example.auctionapp.service;

import com.example.auctionapp.model.Category;
import com.example.auctionapp.request.CategoryAddRequest;
import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<Category> getCategories();
    Category getCategoryById(final UUID id);
    Category addCategory(final CategoryAddRequest categoryRequest);
    Category updateCategory(final UUID id, final CategoryAddRequest categoryRequest);
    void deleteCategory(final UUID id);
    List<Category> getTopLevelCategories();
    List<Category> getCategoriesWithSubcategories();
}
