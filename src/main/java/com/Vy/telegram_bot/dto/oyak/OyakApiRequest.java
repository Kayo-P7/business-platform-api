package com.Vy.telegram_bot.dto.oyak;

import com.Vy.telegram_bot.model.Dimensions;
import com.Vy.telegram_bot.model.Meta;
import com.Vy.telegram_bot.model.Review;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OyakApiRequest(
        UUID id,
        @NotBlank
        String title,
        @NotBlank
        String description,
        @NotBlank
        String category,
        @Positive
        @NotNull
        BigDecimal price,
        @Positive
        @NotNull
        BigDecimal discountPercentage,
        BigDecimal rating,
        @Positive
        @NotNull
        Integer stock,
        List<String> tags,
        @NotBlank
        String brand,
        @NotBlank
        String sku,
        @Positive
        @NotNull
        Integer weight,
        @NotNull
        Dimensions dimensions,
        @NotBlank
        String warrantyInformation,
        @NotBlank
        String shippingInformation,
        @NotBlank
        String availabilityStatus,
        List<Review> reviews,
        @NotBlank
        String returnPolicy,
        @NotNull
        Integer minimumOrderQuantity,
        Meta meta,
        @NotBlank
        List<String> images,
        @NotBlank
        String thumbnail
) {
}
