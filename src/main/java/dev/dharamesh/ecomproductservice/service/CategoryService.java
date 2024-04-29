package dev.dharamesh.ecomproductservice.service;

import dev.dharamesh.ecomproductservice.dto.CategoryRequestDto;
import dev.dharamesh.ecomproductservice.dto.CategoryResponseDto;
import dev.dharamesh.ecomproductservice.entity.Category;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

public interface CategoryService {
    List<CategoryResponseDto> getAllCategories();
    CategoryResponseDto getCategory(UUID categoryId);

    CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDTO);
    CategoryResponseDto updateCategory(CategoryRequestDto categoryRequestDTO, UUID categoryId);
    String deleteCategory(UUID categoryId);
    double getTotalPriceForCategory(UUID categoryId);
}
