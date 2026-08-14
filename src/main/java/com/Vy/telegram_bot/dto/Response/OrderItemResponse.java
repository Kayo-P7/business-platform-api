package com.Vy.telegram_bot.dto.Response;

import com.Vy.telegram_bot.model.OrderItem;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemResponse(
        UUID productId, UUID orderId, Integer quantity, BigDecimal price
) {
    public OrderItemResponse(OrderItem orderItem){
        this(

                orderItem.getProduct().getId(),
                orderItem.getOrder().getId(),
                orderItem.getQuantity(),
                orderItem.getPrice()
        );
    }

}
