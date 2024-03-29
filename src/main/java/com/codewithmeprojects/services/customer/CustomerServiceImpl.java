package com.codewithmeprojects.services.customer;
import com.codewithmeprojects.entity.user;

import com.codewithmeprojects.entity.Panier;
import com.codewithmeprojects.entity.Product;
import com.codewithmeprojects.repository.PanierRepository;
import com.codewithmeprojects.repository.ProductRepository;
import com.codewithmeprojects.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final PanierRepository panierRepository;
    @Override
    public void addProductToCart(Long userId, Long productId) {
        user user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + userId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException("Product not found with id: " + productId));


        Panier panier = panierRepository.findPanierByUserId(userId);
        if (panier == null) {

            panier = new Panier();
            panier.setUser(user);
        }


        List<Product> products = panier.getProducts();


        Optional<Product> panierProductOptional = products.stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst();

        if (panierProductOptional.isPresent()) {
            Product panierProduct = panierProductOptional.get();
            panierProduct.setQuantite(panierProduct.getQuantite() -1);
            product.setQuantite(product.getQuantite()+1);
            System.out.println(product.getQuantite());
            product.setQuantite(product.getQuantite()+1);
            System.out.println(product.getQuantite());
        } else {

            product.setQuantite(1);
            products.add(product);
        }


        panier.setProducts(products);

        panierRepository.save(panier);
    }
}
