package com.Vy.telegram_bot.dto.Request;

public record RegisterRequest(
        String name,
        String email,
        String phoneNumber,
        String password
) {

}
