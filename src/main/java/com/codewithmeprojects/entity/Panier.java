package com.codewithmeprojects.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
@Entity
@Data
public class Panier {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private user user ;


    @Getter
    @ManyToMany
    @JoinTable(name = "panier_product",joinColumns = @JoinColumn(name="panier_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<>();


    public List<Product> getProducts() {
        return products;
    }
}
