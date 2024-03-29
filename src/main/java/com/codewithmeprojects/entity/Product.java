package com.codewithmeprojects.entity;

import com.codewithmeprojects.dto.ProductDto;
import jakarta.persistence.*;
import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name ="product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  Long id;

    private String name;
    private float  price;
    private Integer quantite ;



    public ProductDto getProductDto() {
        ProductDto productDto = new ProductDto();
        productDto.setId(id);
        productDto.setName(name);
        productDto.setPrice(price);
        productDto.setQuantite(quantite);
        return productDto;
    }
}
