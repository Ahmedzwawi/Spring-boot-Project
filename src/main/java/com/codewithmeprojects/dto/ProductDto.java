package com.codewithmeprojects.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class ProductDto {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  Long id;


    private String name;

    private Float price;

    private Integer quantite;
}
