package com.example.auctionapp.service;

import com.example.auctionapp.model.Category;
import com.example.auctionapp.request.CategoryAddRequest;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<Category> getCategories();
    Category getCategoryById(UUID id);
    Category addCategory(CategoryAddRequest categoryRequest);
    Category updateCategory(UUID id, CategoryAddRequest categoryRequest);
    void deleteCategory(UUID id);
    List<Category> getTopLevelCategories();
}
