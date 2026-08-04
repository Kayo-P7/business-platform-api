package com.Vy.telegram_bot.dto;

import com.Vy.telegram_bot.model.Order;
import com.Vy.telegram_bot.model.OrderItem;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderResponse(
        BigDecimal total
) {
    public OrderResponse(Order order) {
        this(

               order.getTotal()
        );
    }
}
