package dev.dharamesh.ecomproductservice.service;

import dev.dharamesh.ecomproductservice.dto.ProductRequestDto;
import dev.dharamesh.ecomproductservice.dto.ProductResponseDto;
import dev.dharamesh.ecomproductservice.entity.Category;
import dev.dharamesh.ecomproductservice.entity.Product;
import dev.dharamesh.ecomproductservice.entity.ProductRating;
import dev.dharamesh.ecomproductservice.exceptions.CategoryNotFoundException;
import dev.dharamesh.ecomproductservice.exceptions.ProductNotFoundException;
import dev.dharamesh.ecomproductservice.mapper.DtoMapper;
import dev.dharamesh.ecomproductservice.repositories.CategoryRepository;
import dev.dharamesh.ecomproductservice.repositories.ProductRatingRepository;
import dev.dharamesh.ecomproductservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("productService")
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRatingRepository productRatingRepository;
    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for(Product product: products){
            ProductResponseDto productResponseDto = DtoMapper.convertProductToProductResponseDto(product);
            productResponseDtoList.add(productResponseDto);
        }
        return productResponseDtoList;
    }

    @Override
    public ProductResponseDto getProductById(UUID productId) throws ProductNotFoundException {
        Product savedProduct = productRepository.findById(productId).orElseThrow(
                ()-> new ProductNotFoundException("Product not found with id: "+productId)
        );

        ProductResponseDto productResponseDto = DtoMapper.convertProductToProductResponseDto(savedProduct);
        return productResponseDto;
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        Product product = DtoMapper.convertProductRequestDtoToProduct(productRequestDto);
        Category savedCategory = categoryRepository.findById(productRequestDto.getCategoryId()).orElseThrow(
                ()-> new CategoryNotFoundException("Category not found with id: "+productRequestDto.getCategoryId())
        );
        product.setCategory(savedCategory);
        productRatingRepository.save(product.getProductRating());
        product = productRepository.save(product);

        ProductResponseDto productResponseDto = DtoMapper.convertProductToProductResponseDto(product);
        return productResponseDto;
    }

    @Override
    public ProductResponseDto updateProduct(UUID productId ,ProductRequestDto productToUpdate ) {
        Product savedProduct = productRepository.findById(productId).orElseThrow(
                ()-> new ProductNotFoundException("Product not found with id: "+productId)
        );

        savedProduct.setTitle(productToUpdate.getTitle());
        savedProduct.setPrice(productToUpdate.getPrice());
        savedProduct.setImageURL(productToUpdate.getImageURL());
        savedProduct.setDescription(productToUpdate.getDescription());

        savedProduct= productRepository.save(savedProduct);
        ProductResponseDto updatedProctResponseDto = DtoMapper.convertProductToProductResponseDto(savedProduct);

        return updatedProctResponseDto;
    }

    @Override
    public String deleteProduct(UUID productId) {

        productRepository.deleteById(productId);
        return "Deleted Successfully..";
    }
}
