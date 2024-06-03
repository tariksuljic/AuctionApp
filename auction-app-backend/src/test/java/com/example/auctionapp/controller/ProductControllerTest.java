package com.example.auctionapp.controller;

import com.example.auctionapp.AuctionAppBackendApplication;
import com.example.auctionapp.entity.enums.ProductStatus;
import com.example.auctionapp.model.Product;
import com.example.auctionapp.request.GetProductRequest;
import com.example.auctionapp.response.ProductSearchResponse;
import com.example.auctionapp.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(classes = AuctionAppBackendApplication.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void whenGetProductById_thenReturnProduct() throws Exception {
        UUID productId = UUID.randomUUID();
        Product product = new Product();

        product.setId(productId);
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setStartPrice(BigDecimal.valueOf(200));
        product.setStartDate(LocalDateTime.now());
        product.setEndDate(LocalDateTime.now().plusDays(1));
        product.setStatus(ProductStatus.ACTIVE);

        when(productService.getProductById(productId)).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/" + productId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.name").value("Test Product"))
                        .andExpect(jsonPath("$.description").value("Test Description"))
                        .andExpect(jsonPath("$.startPrice").value(200))
                        .andExpect(jsonPath("$.status").value("ACTIVE"));
    }

    @Test
    public void whenGetSortedProductsByPrice_thenReturnSortedProducts() throws Exception {
        UUID categoryId = UUID.randomUUID();
        UUID productId1 = UUID.randomUUID();
        UUID productId2 = UUID.randomUUID();

        Product product1 = new Product(productId1,
                "Test Product 1",
                "Test Description 1",
                BigDecimal.valueOf(200),
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(1),
                ProductStatus.ACTIVE,
                categoryId,
                Collections.emptyList(),
                null,
                0,
                BigDecimal.ZERO);

        Product product2 = new Product(productId2,
                "Test Product 2",
                "Test Description 2",
                BigDecimal.valueOf(100),
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(1),
                ProductStatus.ACTIVE,
                categoryId,
                Collections.emptyList(),
                null,
                0,
                BigDecimal.ZERO);

        Page<Product> productPage = new PageImpl<>(Arrays.asList(product2, product1));

        when(productService.getProducts(any(GetProductRequest.class)))
                .thenReturn(new ProductSearchResponse(productPage, null));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products")
                        .param("category_id", categoryId.toString())
                        .param("search_product", "")
                        .param("sort_criteria", "startPrice")
                        .param("sort_direction", "ASC")
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.products.content[0].startPrice").value(100))
                .andExpect(jsonPath("$.products.content[1].startPrice").value(200));
    }
}
