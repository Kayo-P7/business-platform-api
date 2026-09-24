package com.Vy.telegram_bot.dto.Response;

import com.Vy.telegram_bot.model.Product;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponse(
        UUID id,
        String title,
        String description,
        BigDecimal price,
        Integer stock
) {
    public ProductResponse(Product product){
        this(
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getPrice(),
                product.getStock()
        );
    }
}
