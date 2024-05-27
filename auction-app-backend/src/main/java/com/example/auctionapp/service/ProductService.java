package com.example.auctionapp.service;

import com.example.auctionapp.entity.enums.ProductStatus;
import com.example.auctionapp.model.Product;
import com.example.auctionapp.request.ProductAddRequest;
import com.example.auctionapp.response.BidSummaryResponse;
import com.example.auctionapp.response.ProductBidDetailsResponse;
import com.example.auctionapp.response.ProductSearchResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductSearchResponse getProducts(final UUID categoryId, final String searchProduct, final int page, final int size);
    Product getProductById(final UUID id);
    Product addProduct(final ProductAddRequest productRequest, final List<MultipartFile> images);
    void deleteProduct(final UUID id);
    Page<Product> getProductsByCriteria(final int page, final int size, final String type);
    Product getRandomProduct();
    BidSummaryResponse getBidSummary(final UUID productId);
    Page<ProductBidDetailsResponse> getProductByUserAndStatus(final UUID userId,
                                                              final ProductStatus productStatus,
                                                              final int page,
                                                              final int size);
}
