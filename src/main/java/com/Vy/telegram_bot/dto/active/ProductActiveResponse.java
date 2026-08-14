package com.Vy.telegram_bot.dto.active;

import com.Vy.telegram_bot.model.Product;

import java.util.UUID;

public record ProductActiveResponse(
        UUID id,
        String name,
        String description,
        Boolean active
) {
    public ProductActiveResponse(Product product){
        this(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getActive()
        );
    }
}
