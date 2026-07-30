package com.Vy.telegram_bot.controller;

import com.Vy.telegram_bot.dto.ProductRequest;
import com.Vy.telegram_bot.dto.ProductResponse;
import com.Vy.telegram_bot.model.Product;
import com.Vy.telegram_bot.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> save(@Valid @RequestBody ProductRequest request) {


        ProductResponse v = productService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(v);


    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findProductId(@PathVariable UUID id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok(productService.findAll(pageable));
    }
    //deletar, ByName, findStock, findProductPrice, findProductActive

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProductById(@PathVariable("id") UUID id) {

        productService.deletarProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ProductResponse> findByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(productService.findByName(name));
    }

    @GetMapping("/stocklow/{stock}")
    public ResponseEntity<List<ProductResponse>> findLowStock(@PathVariable("stock") Integer stock) {
        return ResponseEntity.ok(productService.findLowStock(stock));
    }

    @GetMapping("/price/{min}/{max}")
    public ResponseEntity<List<ProductResponse>> findProductByPrice(@PathVariable("min") BigDecimal min, @PathVariable("max") BigDecimal max) {

        return ResponseEntity.ok(productService.findProductsByPrice(min, max));
    }

    @GetMapping("/active/{active}")
    public ResponseEntity<List<ProductResponse>> findActiveProducts(@PathVariable("active") Boolean b) {
        return ResponseEntity.ok(productService.findActiveProducts(b));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@Valid @PathVariable("id") UUID id, @RequestBody ProductRequest productRequest) {
        ProductResponse pr = productService.updateProduct(id, productRequest);
        return ResponseEntity.ok(pr);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAll() {
        productService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/stock/{stock}")
    public ResponseEntity<List<ProductResponse>> findByStock(@PathVariable("stock") Integer Stock) {
        return ResponseEntity.ok(productService.findByStock(Stock));

    }
}

