package com.example.auctionapp.service;

import com.example.auctionapp.AuctionAppBackendApplication;
import com.example.auctionapp.entity.ProductEntity;
import com.example.auctionapp.entity.ProductImageEntity;
import com.example.auctionapp.entity.CategoryEntity;
import com.example.auctionapp.entity.UserEntity;
import com.example.auctionapp.entity.enums.ProductStatus;
import com.example.auctionapp.model.Product;
import com.example.auctionapp.repository.ProductRepository;
import com.example.auctionapp.request.GetProductRequest;
import com.example.auctionapp.response.ProductSearchResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest(classes = AuctionAppBackendApplication.class)
public class ProductServiceTest {
    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Test
    public void whenGetLastChance_thenReturnProductsSortedByEndDateAsc() {
        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setCategoryId(UUID.randomUUID());
        categoryEntity.setName("Sneakers");

        UserEntity userEntity = new UserEntity();

        userEntity.setUserId(UUID.randomUUID());
        userEntity.setEmail("test@example.com");
        userEntity.setFirstName("test");
        userEntity.setLastName("example");
        userEntity.setPassword("testexample");

        ProductEntity productEntity = new ProductEntity();

        productEntity.setProductId(UUID.randomUUID());
        productEntity.setName("Limited Edition Sneakers");
        productEntity.setDescription("Limited time offer");
        productEntity.setStartPrice(BigDecimal.valueOf(200));
        productEntity.setStartDate(LocalDateTime.now());
        productEntity.setEndDate(LocalDateTime.now().plusDays(1));
        productEntity.setStatus(ProductStatus.ACTIVE);
        productEntity.setCategory(categoryEntity);
        productEntity.setUserEntity(userEntity);

        ProductImageEntity productImage = new ProductImageEntity();

        productImage.setImageId(UUID.randomUUID());
        productImage.setImageUrl("http://example.com/image.jpg");
        productImage.setProductEntity(productEntity);

        productEntity.setProductImages(List.of(productImage));

        Page<ProductEntity> pageOfProductEntities = new PageImpl<>(List.of(productEntity));

        when(productRepository.findProductEntitiesByStatusEquals(any(Pageable.class), eq(ProductStatus.ACTIVE)))
                .thenReturn(pageOfProductEntities);

        Page<Product> resultPage = productService.getProductsByCriteria(0, 1, "lastChance");

        assertThat(resultPage.getContent()).hasSize(1);

        Product resultProduct = resultPage.getContent().get(0);

        assertThat(resultProduct.getName()).isEqualTo(productEntity.getName());
        assertThat(resultProduct.getDescription()).isEqualTo(productEntity.getDescription());
        assertThat(resultProduct.getProductImages()).isNotEmpty();
    }

    @Test
    public void whenGetSortedProductsByName_thenReturnSortedProducts() {
        UUID categoryId = UUID.randomUUID();

        GetProductRequest getProductRequest = new GetProductRequest(categoryId, "", "name", "ASC", 0, 10);

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setCategoryId(categoryId);
        categoryEntity.setName("Electronics");

        UserEntity userEntity = new UserEntity();

        userEntity.setFirstName("John");
        userEntity.setLastName("Doe");
        userEntity.setUserId(UUID.randomUUID());

        ProductEntity productA = new ProductEntity(UUID.randomUUID(),
                                                    "A - Product",
                                                "Product A Description",
                                                        BigDecimal.valueOf(300),
                                                        LocalDateTime.now(),
                                                        LocalDateTime.now().plusDays(2),
                                                        ProductStatus.ACTIVE,
                                                        categoryEntity,
                                                        userEntity,
                                            null);
        ProductEntity productB = new ProductEntity(UUID.randomUUID(),
                                                "B - Product",
                                            "Product B Description",
                                                        BigDecimal.valueOf(150),
                                                        LocalDateTime.now().minusDays(1),
                                                        LocalDateTime.now().plusDays(1),
                                                        ProductStatus.ACTIVE,
                                                        categoryEntity,
                                                        userEntity,
                                            null);

        List<ProductEntity> sortedProducts = Arrays.asList(productA, productB);
        Page<ProductEntity> productPage = new PageImpl<>(sortedProducts);

        when(productRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(productPage);

        ProductSearchResponse response = productService.getProducts(getProductRequest);

        List<Product> results = response.getProducts().getContent();

        assertThat(results).hasSize(2);
        assertThat(results.get(0).getName()).isEqualTo("A - Product");
        assertThat(results.get(1).getName()).isEqualTo("B - Product");
    }


    @Test
    public void whenGetLatestProducts_thenReturnProductsSortedByStartDateDesc() {
        UUID categoryId = UUID.randomUUID();

        GetProductRequest getProductRequest = new GetProductRequest(categoryId, "", "startDate", "DESC", 0, 10);

        UserEntity userEntity = new UserEntity();

        userEntity.setUserId(UUID.randomUUID());
        userEntity.setFirstName("John");
        userEntity.setLastName("Doe");

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setCategoryId(categoryId);
        categoryEntity.setName("Electronics");

        ProductEntity recentProduct = new ProductEntity(UUID.randomUUID(),
                                                "Recent Product",
                                                "Just launched",
                                                BigDecimal.valueOf(500),
                                                LocalDateTime.now(),
                                                LocalDateTime.now().plusDays(2),
                                                ProductStatus.ACTIVE,
                                                categoryEntity,
                                                userEntity,
                                                null);

        ProductEntity olderProduct = new ProductEntity(UUID.randomUUID(),
                                                    "Older Product",
                                                    "Launched last week",
                                                    BigDecimal.valueOf(350),
                                                    LocalDateTime.now().minusWeeks(1),
                                                    LocalDateTime.now().plusDays(2),
                                                    ProductStatus.ACTIVE,
                                                    categoryEntity,
                                                    userEntity,
                                                    null);

        List<ProductEntity> sortedProducts = Arrays.asList(recentProduct, olderProduct);

        Page<ProductEntity> productPage = new PageImpl<>(sortedProducts);

        when(productRepository.findAll(any(Specification.class), any(Pageable.class)))
                .thenReturn(productPage);

        ProductSearchResponse response = productService.getProducts(getProductRequest);

        List<Product> results = response.getProducts().getContent();

        assertThat(results).hasSize(2);
        assertThat(results.get(0).getName()).isEqualTo("Recent Product");
        assertThat(results.get(1).getName()).isEqualTo("Older Product");
    }
}
