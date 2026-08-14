package com.Vy.telegram_bot.dto.summary;

import com.Vy.telegram_bot.model.Customer;

import java.util.UUID;

public record CustomerSummary(
        UUID id, String name
) {
    public CustomerSummary(Customer customer){
        this(
                customer.getId(),
                customer.getName()
        );
    }
}
