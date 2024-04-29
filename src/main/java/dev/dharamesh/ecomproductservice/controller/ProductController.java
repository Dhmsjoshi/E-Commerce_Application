package dev.dharamesh.ecomproductservice.controller;

import dev.dharamesh.ecomproductservice.dto.FakeStoreProductResponseDto;
import dev.dharamesh.ecomproductservice.dto.ProductRequestDto;
import dev.dharamesh.ecomproductservice.dto.ProductResponseDto;
import dev.dharamesh.ecomproductservice.entity.Product;
import dev.dharamesh.ecomproductservice.exceptions.InvalidInputException;
import dev.dharamesh.ecomproductservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    @Qualifier("productService")
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(){
        List<ProductResponseDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable("id") UUID id){
        if(id == null){
            throw new InvalidInputException("Input is not correct!");
        }
        ProductResponseDto product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }
    @PostMapping()
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto productRequestDto){
        ProductResponseDto savedProduct = productService.createProduct(productRequestDto);
        return ResponseEntity.ok(savedProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable("id") UUID id,@RequestBody ProductRequestDto productRequestDto){
        ProductResponseDto updatedProduct = productService.updateProduct(id,productRequestDto);
        return ResponseEntity.ok(updatedProduct);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") UUID id){
//        String response = productService.deleteProduct(id);
//        return ResponseEntity.ok(response);
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

}
