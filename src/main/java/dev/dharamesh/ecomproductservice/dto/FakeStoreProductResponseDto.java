package dev.dharamesh.ecomproductservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductResponseDto {
    private int id;
    private String title;
    private String description;
    private double price;
    private String category;
    private String image;
    private FakeStoreProductRatingDto rating;

}
