package com.Vy.telegram_bot.dto.Response;

import com.Vy.telegram_bot.dto.summary.CustomerSummary;
import com.Vy.telegram_bot.enums.OrderStatus;
import com.Vy.telegram_bot.model.Order;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record OrderResponse(
        UUID id,
        OrderStatus status,
        BigDecimal total,
        CustomerSummary customerSummary,
        List<OrderItemResponse> items
) {
    public OrderResponse(Order order) {
        this(
                order.getId(),
                order.getOrderStatus(),
                order.getTotal(),
                new CustomerSummary(order.getCustomer()),
                order.getItems().stream().map(OrderItemResponse::new).toList()
        );
    }
}
