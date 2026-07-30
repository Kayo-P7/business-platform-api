package com.Vy.telegram_bot.repository;

import com.Vy.telegram_bot.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    boolean existsByName(String name);

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByQuantityLessThanEqual(Integer quantity);

    List<Product> findByActive(Boolean b);

    List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);

    List<Product> findByQuantityLessThan(Integer quantity);
    List<Product> findByIdAndQuantity(UUID id, Integer quantity);

}
