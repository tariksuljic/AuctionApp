package com.example.auctionapp.service.implementation;

import com.example.auctionapp.request.CategoryAddRequest;
import com.example.auctionapp.entity.CategoryEntity;
import com.example.auctionapp.model.Category;
import com.example.auctionapp.exceptions.repository.ResourceNotFoundException;
import com.example.auctionapp.repository.CategoryRepository;
import com.example.auctionapp.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import static java.util.stream.Collectors.toList;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getCategories() {
        return this.categoryRepository.findAll()
                .stream()
                .map(CategoryEntity::toDomainModel)
                .collect(toList());
    }

    @Override
    public Category getCategoryById(final UUID id) {
        final CategoryEntity categoryEntity = this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category with the given ID does not exist"));

        return categoryEntity.toDomainModel();
    }

    @Override
    public Category addCategory(final CategoryAddRequest categoryRequest) {
        final CategoryEntity categoryEntity = categoryRequest.toEntity();

        if (categoryRequest.getParentCategoryId() != null) {
            CategoryEntity parentCategoryEntity = this.categoryRepository.findById(categoryRequest.getParentCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Parent category not found"));

            categoryEntity.setParentCategory(parentCategoryEntity);
        }

        return this.categoryRepository.save(categoryEntity).toDomainModel();
    }

    @Override
    public Category updateCategory(final UUID id, final CategoryAddRequest categoryRequest) {
        final CategoryEntity existingCategoryEntity = this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category with the given ID does not exist"));

        existingCategoryEntity.setName(categoryRequest.getName());

        return this.categoryRepository.save(existingCategoryEntity).toDomainModel();
    }

    @Override
    public void deleteCategory(final UUID id) {
        final CategoryEntity categoryEntity = this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        this.categoryRepository.delete(categoryEntity);
    }

    public List<Category> getCategoriesWithSubcategories() {
        List<CategoryEntity> topLevelCategories = categoryRepository.findByParentCategoryIsNull();

        return topLevelCategories.stream()
                .map(CategoryEntity::toDomainModel)
                .collect(toList());
    }

    @Override
    public List<Category> getTopLevelCategories() {
        return this.categoryRepository.findByParentCategoryIsNull()
                .stream()
                .map(CategoryEntity::toDomainModel)
                .collect(toList());
    }
}
