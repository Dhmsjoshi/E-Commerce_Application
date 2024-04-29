package dev.dharamesh.ecomproductservice.dto;

import dev.dharamesh.ecomproductservice.entity.Category;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class ProductResponseDto {
    private UUID productId;
    private String title;
    private double price;
    private String description;
    private String categoryName;
    private String ImageURL;
    private ProductRatingDto rating;
    private Instant createdAt;
    private Instant updatedAt;


}
