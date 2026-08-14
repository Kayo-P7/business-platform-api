package com.Vy.telegram_bot.dto.Response;

import com.Vy.telegram_bot.model.Customer;

import java.util.UUID;

public record CustomerResponse(
        UUID id,
        String name,
        String email,
        Boolean activeAt,
        String phoneNumber

) {
    public CustomerResponse(Customer customer) {
        this(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getActive(),
                customer.getPhoneNumber()
        );
    }
}
