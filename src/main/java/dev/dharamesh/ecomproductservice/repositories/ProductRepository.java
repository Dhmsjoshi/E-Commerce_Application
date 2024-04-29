package dev.dharamesh.ecomproductservice.repositories;

import dev.dharamesh.ecomproductservice.entity.Product;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Override
    Product  save(Product product);
}
