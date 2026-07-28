package com.Vy.telegram_bot.controller;

import com.Vy.telegram_bot.dto.ProductRequest;
import com.Vy.telegram_bot.dto.ProductResponse;
import com.Vy.telegram_bot.model.Product;
import com.Vy.telegram_bot.service.ProductService;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> save(@RequestBody ProductRequest request) {

        return ResponseEntity.ok(productService.createProduct(request));


    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findProductId(@PathVariable UUID id) {

        return productService.findById(id).map(ProductResponse::new).
                map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }
    //deletar, ByName, findStock, findProductPrice, findProductActive

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletarProductById(@PathVariable("id") UUID id) {

        if (productService.deletarProduct(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Optional<Product>> findByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(productService.findByName(name));
    }

    @GetMapping("/stock{stock}")
    public ResponseEntity<List<ProductResponse>> findLowStock() {
        return ResponseEntity.ok(productService.findLowStock());
    }

    @GetMapping("/price/{min}/{max}")
    public ResponseEntity<List<ProductResponse>> findProductByPrice(@PathVariable("min") BigDecimal min, @PathVariable("max") BigDecimal max) {

        return ResponseEntity.ok(productService.findProductsByPrice(min, max));
    }

    @GetMapping("/active")
    public ResponseEntity<List<ProductResponse>> findActiveProducts() {
        return ResponseEntity.ok(productService.findActiveProducts());
    }
}
