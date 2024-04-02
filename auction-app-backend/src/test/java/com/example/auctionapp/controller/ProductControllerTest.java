package com.example.auctionapp.controller;

import com.example.auctionapp.model.Product;
import com.example.auctionapp.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void whenGetProductsPaginated_thenReturnPaginatedProducts() throws Exception {
        Product product = new Product();

        product.setId(UUID.randomUUID());
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setStartPrice(BigDecimal.valueOf(200));
        product.setStartDate(LocalDateTime.now());
        product.setEndDate(LocalDateTime.now().plusDays(1));
        product.setStatus("ACTIVE");

        Page<Product> pageOfProducts = new PageImpl<>(List.of(product), PageRequest.of(0, 8), 1);

        Mockito.when(productService.getProducts(0, 8)).thenReturn(pageOfProducts);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products?page=0&size=8")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.content[0].name").value("Test Product"))
                        .andExpect(jsonPath("$.content[0].description").value("Test Description"));
    }

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
        product.setStatus("ACTIVE");

        Mockito.when(productService.getProductById(productId)).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/" + productId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.name").value("Test Product"))
                        .andExpect(jsonPath("$.description").value("Test Description"))
                        .andExpect(jsonPath("$.startPrice").value(200))
                        .andExpect(jsonPath("$.status").value("ACTIVE"));
    }
}
