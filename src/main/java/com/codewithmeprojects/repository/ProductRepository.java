package com.codewithmeprojects.repository;

import com.codewithmeprojects.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    boolean existsByName(String name);


    //Optional<Product> findById(Long aLong) ;
}
