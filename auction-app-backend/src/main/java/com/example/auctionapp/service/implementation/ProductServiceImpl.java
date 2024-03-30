package com.example.auctionapp.service.implementation;

import com.example.auctionapp.request.ProductAddRequest;
import com.example.auctionapp.entity.ProductEntity;
import com.example.auctionapp.model.Product;
import com.example.auctionapp.exceptions.repository.ResourceNotFoundException;
import com.example.auctionapp.repository.CategoryRepository;
import com.example.auctionapp.repository.ProductRepository;
import com.example.auctionapp.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<Product> getProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return this.productRepository.findAll(pageable).map(ProductEntity::toDomainModel);
    }

    @Override
    public Product getProductById(UUID id) {
        ProductEntity productEntity = this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with the given ID does not exist"));

        return productEntity.toDomainModel();
    }

    @Override
    public Product addProduct(ProductAddRequest productRequest) {
        ProductEntity productEntity = productRequest.toEntity();

        if (productRequest.getCategoryId() != null) {
            productEntity.setCategory(categoryRepository.findById(productRequest.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category with the given ID does not exist")));
        }

        return this.productRepository.save(productEntity).toDomainModel();
    }

    @Override
    public Product updateProduct(UUID id, ProductAddRequest productRequest) {
        ProductEntity existingProductEntity = this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with the given ID does not exist"));

        existingProductEntity.setName(productRequest.getName());
        existingProductEntity.setDescription(productRequest.getDescription());
        existingProductEntity.setStartPrice(productRequest.getStartPrice());
        existingProductEntity.setStartDate(productRequest.getStartDate());
        existingProductEntity.setEndDate(productRequest.getEndDate());
        existingProductEntity.setImageUrl(productRequest.getImageUrl());
        existingProductEntity.setStatus(productRequest.getStatus());

        if (productRequest.getCategoryId() != null) {
            existingProductEntity.setCategory(categoryRepository.findById(productRequest.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found")));
        }

        return this.productRepository.save(existingProductEntity).toDomainModel();
    }

    @Override
    public void deleteProduct(UUID id) {
        ProductEntity productEntity = this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        this.productRepository.delete(productEntity);
    }

    @Override
    public Page<Product> getProductsByCriteria(int page, int size, String type) {
        Sort.Direction direction = Sort.Direction.DESC;
        String sortBy = "startDate";

        if ("lastChance".equals(type)) {
            direction = Sort.Direction.ASC;
            sortBy = "endDate";
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<ProductEntity> productPage = productRepository.findAll(pageable);

        return productPage.map(ProductEntity::toDomainModel);
    }

    @Override
    public Product getRandomProduct() {
        Optional<ProductEntity> randomProductEntity = productRepository.findRandomProduct();

        if (randomProductEntity.isEmpty()) {
            throw new ResourceNotFoundException("No products available");
        }

        return randomProductEntity.get().toDomainModel();
    }
}

