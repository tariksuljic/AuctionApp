package com.example.auctionapp.controller;

import com.example.auctionapp.model.Category;
import com.example.auctionapp.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
@AutoConfigureMockMvc
public class CategoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Test
    public void whenGetTopLevelCategories_thenReturnTopLevelCategoriesList() throws Exception {
        Category category = new Category();

        category.setId(UUID.randomUUID());
        category.setName("Electronics");

        List<Category> topLevelCategories = Arrays.asList(category);

        Mockito.when(categoryService.getTopLevelCategories()).thenReturn(topLevelCategories);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/categories/top-level")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$[0].name").value("Electronics"));
    }
}
