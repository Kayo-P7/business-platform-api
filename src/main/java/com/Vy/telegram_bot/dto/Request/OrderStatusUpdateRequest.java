package com.Vy.telegram_bot.dto.Request;

import com.Vy.telegram_bot.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;

public record OrderStatusUpdateRequest(
        @NotNull
        OrderStatus status
) {
}
