package com.Vy.telegram_bot.repository;

import com.Vy.telegram_bot.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    List<Customer> findByName(String name);
    boolean existsByEmail(String email);
}
