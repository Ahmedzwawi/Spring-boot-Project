package com.codewithmeprojects.services.admin;

import com.codewithmeprojects.dto.ProductDto;
import com.codewithmeprojects.entity.Product;
import com.codewithmeprojects.repository.ProductRepository;
import com.codewithmeprojects.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private  final ProductRepository productRepository ;
    private final UserRepository userRepository ;


    @Override
    public boolean addproduct(ProductDto productDto)throws IOException {

            try {  Product product = new Product();
                product.setName (productDto.getName());
                product.setPrice(productDto.getPrice());
                product.setQuantite(productDto.getQuantite());
                productRepository.save(product);
                return true;


        }catch(Exception e) {
            return false;
        }
    }
    @Override
    public void Deleteproduct(long id) {

        productRepository.deleteById(id);
    }
    @Override
    public List<ProductDto> getAllProducts() {
        return  productRepository.findAll().stream().map(Product :: getProductDto).collect(Collectors.toList());
    }

}
