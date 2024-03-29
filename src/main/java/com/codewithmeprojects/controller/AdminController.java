package com.codewithmeprojects.controller;

import com.codewithmeprojects.dto.ProductDto;
import com.codewithmeprojects.services.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    @Autowired
    private final AdminService adminService;
    @PostMapping("/product")
    public ResponseEntity<?> addproduct(@RequestBody ProductDto productDto) throws IOException {

       boolean done= adminService.addproduct(productDto);
       if(done) {
           return ResponseEntity.status(HttpStatus.CREATED).build();
       }else {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
       }


    }

    @GetMapping("/allproducts")
    public ResponseEntity<?> getAllProducts(){
        return ResponseEntity.ok(adminService.getAllProducts());
    }
    @DeleteMapping("/product/{id}")
    public  ResponseEntity<Void> Deleteproduct(@PathVariable  Long id) {
        adminService.Deleteproduct(id);
        return ResponseEntity.ok(null);

    }

}
