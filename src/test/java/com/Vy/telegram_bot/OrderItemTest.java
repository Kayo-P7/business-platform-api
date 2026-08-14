package com.Vy.telegram_bot;

import com.Vy.telegram_bot.model.Order;
import com.Vy.telegram_bot.model.OrderItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderItemTest {

    @Test
    void shouldCalculateOrderTotal() {

        OrderItem Item1 = new OrderItem();
        Item1.setQuantity(5);
        Item1.setPrice(new BigDecimal("5900"));
//        OrderItem Item2 = new OrderItem();
//        Item2.setQuantity(1);
//        Item2.setPrice(new BigDecimal("6000"));
//
//
//
//        OrderItem Item2 = new OrderItem();
//        Item2.setQuantity(2);
//        Item2.setPrice(new BigDecimal("3000"));


        Order order = new Order();
        order.getItems().add(Item1);
//        order.getItems().add(Item2);
//        order.getItems().add(Item2);
//        BigDecimal total = order.getTotal();
//        assertEquals(new BigDecimal("35500"), total);
        BigDecimal total = Item1.getTotal();
        assertEquals(new BigDecimal("29500"), total);

    }
}
