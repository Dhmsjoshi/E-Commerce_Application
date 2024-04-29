package dev.dharamesh.ecomproductservice.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ProductRating extends BaseModel{
    private double rate;
    private int count;
}
