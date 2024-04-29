package dev.dharamesh.ecomproductservice.service;

import dev.dharamesh.ecomproductservice.dto.ProductRequestDto;
import dev.dharamesh.ecomproductservice.dto.ProductResponseDto;
import dev.dharamesh.ecomproductservice.entity.Product;
import dev.dharamesh.ecomproductservice.exceptions.ProductNotFoundException;

import java.util.List;
import java.util.UUID;

public interface ProductService {
     List<ProductResponseDto> getAllProducts();
     ProductResponseDto getProductById(UUID productId) throws ProductNotFoundException;
     ProductResponseDto createProduct(ProductRequestDto product);
     ProductResponseDto updateProduct(UUID productId, ProductRequestDto productToUpdate );
     String deleteProduct(UUID productId);
}
