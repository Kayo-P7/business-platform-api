package com.Vy.telegram_bot.service;

import com.Vy.telegram_bot.dto.active.ProductActiveRequest;
import com.Vy.telegram_bot.dto.active.ProductActiveResponse;
import com.Vy.telegram_bot.dto.Request.ProductRequest;
import com.Vy.telegram_bot.dto.Response.ProductResponse;
import com.Vy.telegram_bot.exception.ProductAlreadyExistsException;
import com.Vy.telegram_bot.exception.ProductInactiveException;
import com.Vy.telegram_bot.exception.ProductNotFoundException;
import com.Vy.telegram_bot.model.Product;
import com.Vy.telegram_bot.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {


    private final ProductRepository productRepository;

    //private static final int LOW_STOCK = 9;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public ProductResponse save(ProductRequest request) {
        Product product = new Product();

        if (productRepository.existsByName(request.name())) {
            throw new ProductAlreadyExistsException("Product Already exist! try again");
        }

        BeanUtils.copyProperties(request, product);
        product.setCreatedAt(LocalDateTime.now());
        product.setActive(true);
        productRepository.save(product);
        return new ProductResponse(product);

    }

//    public void addProduct(Product product) {
//       products.add(product);
//    }

    public Page<ProductResponse> findAll(Pageable pageable) {
        return productRepository.findAll(pageable).map(ProductResponse::new);
    }

    public void deletarProduct(UUID id) {


        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Id not found!");
        }

        productRepository.deleteById(id);

    }

    public ProductResponse findById(UUID id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found!"));
        return new ProductResponse(product);
    }

    public ProductResponse findByName(String name) {
        Product product = productRepository.findByNameContainingIgnoreCase(name).stream().findFirst().orElseThrow(() -> new ProductNotFoundException("Name not found!"));
        return new ProductResponse(product);
    }

    public List<ProductResponse> findLowStock(Integer stock) {
        return productRepository.findByQuantityLessThanEqual(stock).stream().
                sorted(Comparator.comparingInt(Product::getQuantity))
                .map(ProductResponse::new).toList();
    }

    public List<ProductResponse> findActiveProducts(Boolean active) {
        return productRepository.findByActive(active).stream().map(ProductResponse::new).toList();
    }

    public List<ProductResponse> findProductsByPrice(BigDecimal min, BigDecimal max) {
        return productRepository.findByPriceBetween(min, max).stream().map(ProductResponse::new).toList();
    }

    public ProductResponse updateProduct(UUID id, ProductRequest productRequest) {


        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Id not found!"));
        if (product.getActive() == false) {
            throw new ProductInactiveException("Product inactive, dont can update");
        }
        BeanUtils.copyProperties(productRequest, product, "id", "createdAt", "active");
        productRepository.save(product);
        return new ProductResponse(product);
    }

    public void deleteAll() {
        productRepository.deleteAll();
    }

    @Transactional
    public ProductActiveResponse updateActive(UUID id, ProductActiveRequest request) {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found!"));

        product.setActive(request.active());

        return new ProductActiveResponse(product);
    }
}