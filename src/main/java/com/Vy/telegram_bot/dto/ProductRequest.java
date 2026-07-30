package com.Vy.telegram_bot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank
        String name,
        @NotBlank
        String description,
        @Positive
        BigDecimal price,
        @PositiveOrZero
        Integer quantity
        //representando a entrada do usuario
) {

}
