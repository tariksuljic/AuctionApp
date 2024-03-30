package com.example.auctionapp.service;

import com.example.auctionapp.model.Product;
import com.example.auctionapp.request.ProductAddRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    Page<Product> getProducts(int page, int size);
    Product getProductById(UUID id);
    Product addProduct(ProductAddRequest productRequest);
    Product updateProduct(UUID id, ProductAddRequest productRequest);
    void deleteProduct(UUID id);
    Page<Product> getProductsByCriteria(int page, int size, String type);
    Product getRandomProduct();
}
