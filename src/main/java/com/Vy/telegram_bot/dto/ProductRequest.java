package com.Vy.telegram_bot.dto;

import java.math.BigDecimal;

public record ProductRequest(
        String name,
        String description,
        BigDecimal price,
        Integer quantity
        //representando a entrada do usuario
) {

}
