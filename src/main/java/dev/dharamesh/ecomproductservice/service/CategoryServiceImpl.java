package dev.dharamesh.ecomproductservice.service;

import dev.dharamesh.ecomproductservice.dto.CategoryRequestDto;
import dev.dharamesh.ecomproductservice.dto.CategoryResponseDto;
import dev.dharamesh.ecomproductservice.entity.Category;
import dev.dharamesh.ecomproductservice.entity.Product;
import dev.dharamesh.ecomproductservice.exceptions.CategoryNotFoundException;
import dev.dharamesh.ecomproductservice.mapper.DtoMapper;
import dev.dharamesh.ecomproductservice.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<CategoryResponseDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponseDto> categoryResponseDtos = new ArrayList<>();
        for(Category category: categories){
            CategoryResponseDto categoryResponseDto = DtoMapper.convertCategoryToCategoryResponseDto(category);
            categoryResponseDtos.add(categoryResponseDto);

        }
        return categoryResponseDtos;
    }

    @Override
    public CategoryResponseDto getCategory(UUID categoryId) {
        Category savedCategory = categoryRepository.findById(categoryId).orElseThrow(
                ()-> new CategoryNotFoundException("Category not found with id: "+categoryId)
        );


        return DtoMapper.convertCategoryToCategoryResponseDto(savedCategory);
    }


    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDTO) {
        Category category  = new Category();
        category.setName(categoryRequestDTO.getCategoryName());
        category = categoryRepository.save(category);
        return DtoMapper.convertCategoryToCategoryResponseDto(category);
    }

    @Override
    public CategoryResponseDto updateCategory(CategoryRequestDto categoryRequestDTO, UUID categoryId) {
        Category savedCategory = categoryRepository.findById(categoryId).orElseThrow(
                ()-> new CategoryNotFoundException("Category not found with id: "+categoryId)
        );

        savedCategory.setName(categoryRequestDTO.getCategoryName());
        savedCategory = categoryRepository.save(savedCategory);
        return DtoMapper.convertCategoryToCategoryResponseDto(savedCategory);
    }

    @Override
    public String deleteCategory(UUID categoryId) {
        categoryRepository.deleteById(categoryId);

        return "Deleted Successfully..";
    }

    @Override
    public double getTotalPriceForCategory(UUID categoryId) {
        Category savedCategory = categoryRepository.findById(categoryId).orElseThrow(
                ()-> new CategoryNotFoundException("Category not found with id: "+categoryId)
        );

        if(savedCategory.getProducts().isEmpty()){
            return 0.0;
        }else{
            double sum = 0;
            for(Product product: savedCategory.getProducts()){
                sum += product.getPrice();
            }
            return sum;
        }
    }
}
