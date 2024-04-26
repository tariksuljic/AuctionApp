package com.example.auctionapp.controller;

import com.example.auctionapp.model.Product;
import com.example.auctionapp.response.ProductSearchResponse;
import com.example.auctionapp.service.ProductService;
import com.example.auctionapp.request.ProductAddRequest;
import com.example.auctionapp.util.SecurityRoles;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
@SecurityRequirement(name = "JWT Security")
public class ProductController {
    private final ProductService productService;

    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ProductSearchResponse getProducts(
            @RequestParam(value = "page", defaultValue = "0") final int page,
            @RequestParam(value = "size", defaultValue = "8") final int size,
            @RequestParam(value = "category_id", required = false) final UUID categoryId,
            @RequestParam(value = "search_product", required = false) final String searchProduct
    ) {
        return this.productService.getProducts(categoryId, searchProduct, page, size);
    }

    @PreAuthorize(SecurityRoles.ALL)
    @PostMapping
    public Product addProduct(@RequestBody final ProductAddRequest product) {
        return this.productService.addProduct(product);
    }

    @GetMapping(path = "/{id}")
    public Product getProductById(@PathVariable final UUID id) {
        return this.productService.getProductById(id);
    }

    @PreAuthorize(SecurityRoles.ALL)
    @PutMapping(path = "/{id}")
    public Product updateProduct(@PathVariable final UUID id, @RequestBody final ProductAddRequest product) {
        return this.productService.updateProduct(id, product);
    }

    @PreAuthorize(SecurityRoles.ALL)
    @DeleteMapping(path = "/{id}")
    public void deleteProduct(@PathVariable final UUID id) {
        this.productService.deleteProduct(id);
    }

    @GetMapping("/criteria")
    public Page<Product> getProductsByCriteria(
            @RequestParam(value = "page", defaultValue = "0") final int page,
            @RequestParam(value = "size", defaultValue = "8") final int size,
            @RequestParam(value = "type", defaultValue = "newArrivals") final String type) {
        return productService.getProductsByCriteria(page, size, type);
    }

    @GetMapping("/random")
    public Product getRandomProduct() {
        return this.productService.getRandomProduct();
    }
}
