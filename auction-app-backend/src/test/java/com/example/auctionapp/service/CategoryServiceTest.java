package com.example.auctionapp.service;

import com.example.auctionapp.AuctionAppBackendApplication;
import com.example.auctionapp.repository.CategoryRepository;
import com.example.auctionapp.entity.CategoryEntity;
import com.example.auctionapp.model.Category;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest(classes = AuctionAppBackendApplication.class)
public class CategoryServiceTest {

    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @Test
    public void whenGetTopLevelCategories_thenReturnTopLevelCategoriesList() {
        CategoryEntity topLevelCategory = new CategoryEntity(UUID.randomUUID(), "Electronics", null);
        List<CategoryEntity> topLevelCategories = Arrays.asList(topLevelCategory);

        when(categoryRepository.findByParentCategoryIsNull()).thenReturn(topLevelCategories);

        List<Category> result = categoryService.getTopLevelCategories();

        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getName()).isEqualTo(topLevelCategory.getName());
        assertThat(result.get(0).getParentCategory()).isNull();
    }
}
