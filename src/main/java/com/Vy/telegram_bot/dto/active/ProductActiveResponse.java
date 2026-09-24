package com.Vy.telegram_bot.dto.active;

import com.Vy.telegram_bot.model.Product;

import java.util.UUID;

public record ProductActiveResponse(
        UUID id,
        String title,
        String description,
        Boolean active
) {
    public ProductActiveResponse(Product product){
        this(
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getActive()
        );
    }
}
