package com.codewithmeprojects.services.admin;

import com.codewithmeprojects.dto.ProductDto;

import java.io.IOException;
import java.util.List;

public interface AdminService  {
    boolean addproduct(ProductDto productDto) throws IOException;
    public List<ProductDto> getAllProducts();
    public void Deleteproduct(long id);


}
