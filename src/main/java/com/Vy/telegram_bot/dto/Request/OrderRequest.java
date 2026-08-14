package com.Vy.telegram_bot.dto.Request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record OrderRequest(
        @NotNull
        UUID customerId,
        @NotNull
        List<@Valid OrderItemRequest> items
) {

}
