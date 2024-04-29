package dev.dharamesh.ecomproductservice.controller;

import dev.dharamesh.ecomproductservice.dto.CategoryRequestDto;
import dev.dharamesh.ecomproductservice.dto.CategoryResponseDto;
import dev.dharamesh.ecomproductservice.service.CategoryService;
import dev.dharamesh.ecomproductservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    @Qualifier("categoryService")
    private CategoryService categoryService;



    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories(){
        List<CategoryResponseDto> categoryList = categoryService.getAllCategories();
        return ResponseEntity.ok(categoryList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getCategoryById(@PathVariable("id") UUID categoryId){
        CategoryResponseDto savedCategory = categoryService.getCategory(categoryId);
        return ResponseEntity.ok(savedCategory);
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody CategoryRequestDto categoryRequestDto){
        CategoryResponseDto savedCategory = categoryService.createCategory(categoryRequestDto);

        return ResponseEntity.ok(savedCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(@RequestBody  CategoryRequestDto categoryRequestDto,@PathVariable("id") UUID categoryId){
        CategoryResponseDto updatedCategory = categoryService.updateCategory(categoryRequestDto,categoryId);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") UUID categoryId){
        String response = categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok(response);
    }


}
