package com.codewithmeprojects.controller;


import com.codewithmeprojects.services.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor

public class CustomerController {

    @Autowired
    private final CustomerService customerService;
    @PostMapping("/add")
    public ResponseEntity<String> addProductToCart(@RequestParam Long userId, @RequestParam Long productId) {
        try {
            customerService.addProductToCart(userId, productId);
            return ResponseEntity.ok("Product added to cart ");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding product to cart: " + e.getMessage());
        }
    }
}
