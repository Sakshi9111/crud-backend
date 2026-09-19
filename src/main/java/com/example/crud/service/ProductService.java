package com.example.crud.service;

import com.example.crud.exception.ProductNotFoundException;
import com.example.crud.model.Product;
import com.example.crud.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(
            ProductRepository productRepository
    ) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(
                        () -> new ProductNotFoundException(id)
                );
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public Product update(
            Long id,
            Product productDetails
    ) {

        Product product = findById(id);

        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        product.setQuantity(productDetails.getQuantity());

        return productRepository.save(product);
    }

    public void delete(Long id) {

        Product product = findById(id);

        productRepository.delete(product);
    }
}
