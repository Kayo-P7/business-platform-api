package com.Vy.telegram_bot.repository;

import com.Vy.telegram_bot.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    boolean existsByTitle(String name);

    List<Product> findByTitleContainingIgnoreCase(String name);

    List<Product> findByStockLessThanEqual(Integer quantity);

    List<Product> findByActive(Boolean b);

    List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);

    List<Product> findByStockLessThan(Integer quantity);
    List<Product> findByIdAndStock(UUID id, Integer quantity);

}
