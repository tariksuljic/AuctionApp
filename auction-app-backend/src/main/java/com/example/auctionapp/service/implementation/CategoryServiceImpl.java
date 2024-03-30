package com.example.auctionapp.service.implementation;

import com.example.auctionapp.request.CategoryAddRequest;
import com.example.auctionapp.entity.CategoryEntity;
import com.example.auctionapp.model.Category;
import com.example.auctionapp.exceptions.repository.ResourceNotFoundException;
import com.example.auctionapp.repository.CategoryRepository;
import com.example.auctionapp.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import static java.util.stream.Collectors.toList;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
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
    public Category getCategoryById(UUID id) {
        CategoryEntity categoryEntity = this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category with the given ID does not exist"));

        return categoryEntity.toDomainModel();
    }

    @Override
    public Category addCategory(CategoryAddRequest categoryRequest) {
        CategoryEntity categoryEntity = categoryRequest.toEntity();

        if (categoryRequest.getParentCategoryId() != null) {
            CategoryEntity parentCategoryEntity = this.categoryRepository.findById(categoryRequest.getParentCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Parent category not found"));

            categoryEntity.setParentCategory(parentCategoryEntity);
        }

        return this.categoryRepository.save(categoryEntity).toDomainModel();
    }

    @Override
    public Category updateCategory(UUID id, CategoryAddRequest categoryRequest) {
        CategoryEntity existingCategoryEntity = this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category with the given ID does not exist"));

        existingCategoryEntity.setName(categoryRequest.getName());

        return this.categoryRepository.save(existingCategoryEntity).toDomainModel();
    }

    @Override
    public void deleteCategory(UUID id) {
        CategoryEntity categoryEntity = this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        this.categoryRepository.delete(categoryEntity);
    }

    @Override
    public List<Category> getTopLevelCategories() {
        return this.categoryRepository.findByParentCategoryIsNull()
                .stream()
                .map(CategoryEntity::toDomainModel)
                .collect(toList());
    }
}
