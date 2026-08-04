package com.Vy.telegram_bot.repository;

import com.Vy.telegram_bot.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
