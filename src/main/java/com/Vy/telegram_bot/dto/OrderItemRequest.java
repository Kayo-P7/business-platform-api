package com.Vy.telegram_bot.dto;

import java.util.UUID;

public record OrderItemRequest(
        UUID productId,
        Integer quantity
        ) {
}
