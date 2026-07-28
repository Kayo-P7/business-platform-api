package com.Vy.telegram_bot.service;

import com.Vy.telegram_bot.dto.ProductRequest;
import com.Vy.telegram_bot.dto.ProductResponse;
import com.Vy.telegram_bot.model.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();
    private static final int LOW_STOCK = 5;

    public ProductResponse createProduct(ProductRequest request) {
        Product product = new Product();

        product.setId(UUID.randomUUID());
        product.setName(request.name());
        product.setPrice(request.price());
        product.setDescription(request.description());
        product.setQuantity(request.quantity());
        product.setCreatedAt(LocalDateTime.now());
        product.setActive(true);


        products.add(product);
        return new ProductResponse(product);

    }


    public List<ProductResponse> listProducts() {
        return products.stream().map(ProductResponse::new).toList();

    }

//    public void addProduct(Product product) {
//       products.add(product);
//    }

    public boolean deletarProduct(UUID id) {
        return products.removeIf(x -> x.getId().equals(id));

    }

    public Optional<Product> findById(UUID id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public Optional<Product> findByName(String name) {
        return products.stream().filter(p -> p.getName()
                .equalsIgnoreCase(name)).findFirst();
    }

    public List<ProductResponse> findLowStock() {
        return products.stream().filter(p -> p.getQuantity() < LOW_STOCK).
                sorted(Comparator.comparingInt(Product::getQuantity))
                .map(ProductResponse::new).toList();
    }

    public List<ProductResponse> findActiveProducts() {
        return products.stream().filter(Product::getActive).map(ProductResponse::new).toList();
    }

    public List<ProductResponse> findProductsByPrice(BigDecimal min, BigDecimal max) {
        return products.stream().filter(p -> p.getPrice().compareTo(min) >= 0 && p.getPrice().compareTo(max) <= 0).map(ProductResponse::new).toList();
    }

    public List<ProductResponse> findAll(){
        return products.stream().map(ProductResponse::new).toList();
    }
}
