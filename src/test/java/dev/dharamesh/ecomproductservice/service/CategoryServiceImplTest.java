package dev.dharamesh.ecomproductservice.service;

import dev.dharamesh.ecomproductservice.entity.Category;
import dev.dharamesh.ecomproductservice.entity.Product;
import dev.dharamesh.ecomproductservice.exceptions.CategoryNotFoundException;
import dev.dharamesh.ecomproductservice.repositories.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryServiceImplTest {
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this); // not required nowadays
    }

    @Test
    public void testGetTotalPriceForMultipleProductsUnderCategory(){
        //this method will return total cost for all products under category

        //Arrange
        UUID categoryId = UUID.randomUUID();
        Optional<Category> categoryOptionalMockData = getCategoryMockData();
        Mockito.when(categoryRepository.findById(categoryId)).thenReturn(categoryOptionalMockData);
        double expectedTotalCost = 300.0;

        //Act
        double actualtotalCost = categoryService.getTotalPriceForCategory(categoryId);

        //Assert
        Assertions.assertEquals(actualtotalCost, expectedTotalCost);
    }

    @Test
    public void testGetTotalPriceForZeroProductsUnderCategory(){
        //this method will return total cost for zero products under category

        //Arrange
        UUID categoryId = UUID.randomUUID();
        Optional<Category> categoryOptionalMockData = getCategoryMockDataWithZeroProducts();
        Mockito.when(categoryRepository.findById(categoryId)).thenReturn(categoryOptionalMockData);
        double expectedTotalCost = 0.0;

        //Act
        double actualTotalCost = categoryService.getTotalPriceForCategory(categoryId);

        //Assert
        Assertions.assertEquals(actualTotalCost,expectedTotalCost);
        Mockito.verify(categoryRepository).findById(categoryId);

    }

    @Test
    public void testCategoryNotFoundExceptionThrown(){
        //Arrange
        UUID categoryId = UUID.randomUUID();
        Mockito.when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        //Act & Assert
        Assertions.assertThrows(CategoryNotFoundException.class,
                ()-> categoryService.getTotalPriceForCategory(categoryId));

    }

    public Optional<Category> getCategoryMockDataWithZeroProducts(){
        Category category = new Category();
        category.setId(UUID.randomUUID());
        category.setName("category");

        List<Product> products = new ArrayList<>();
        category.setProducts(products);
        return Optional.of(category);
    }

    public Optional<Category> getCategoryMockData(){
        Category category = new Category();
        category.setId(UUID.randomUUID());
        category.setName("category");

        Product product1 = new Product();
        product1.setCategory(category);
        product1.setTitle("product1");
        product1.setDescription("prod1 desc.");
        product1.setPrice(100);

        Product product2 = new Product();
        product2.setCategory(category);
        product2.setTitle("product2");
        product2.setDescription("prod2 desc.");
        product2.setPrice(200);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        category.setProducts(products);
        return Optional.of(category);
    }



}