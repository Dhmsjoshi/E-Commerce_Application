package dev.dharamesh.ecomproductservice.repositories;

import dev.dharamesh.ecomproductservice.entity.ProductRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRatingRepository extends JpaRepository<ProductRating, UUID> {
}
