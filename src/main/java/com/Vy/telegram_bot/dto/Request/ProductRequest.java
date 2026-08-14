package com.Vy.telegram_bot.dto.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ProductRequest(

        @NotBlank
        @Schema(
                description = "Product name",
                example = "Notebook gamer"
        )
        String name,
        @NotBlank
        String description,
        @Schema(
                description = "Product price",
                example = "99.88"
        )
        @Positive
        BigDecimal price,
        @Schema
                (
                        description = "Avaliable quantity",
                        example = "20"
                )
        @PositiveOrZero
        Integer quantity
        //representando a entrada do usuario
) {

}
