package com.example.crud.controller;

import com.example.crud.model.Product;
import com.example.crud.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(
        origins = {
                "http://localhost:5173",
                "http://localhost:3000"
        }
)
public class ProductController {

    private final ProductService productService;

    public ProductController(
            ProductService productService
    ) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {

        return ResponseEntity.ok(
                productService.findAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                productService.findById(id)
        );
    }

    @PostMapping
    public ResponseEntity<Product> create(
            @Valid @RequestBody Product product
    ) {

        Product created =
                productService.create(product);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(
            @PathVariable Long id,
            @Valid @RequestBody Product product
    ) {

        Product updated =
                productService.update(id, product);

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {

        productService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
