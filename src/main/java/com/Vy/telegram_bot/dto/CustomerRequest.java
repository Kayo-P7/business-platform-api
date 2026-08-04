package com.Vy.telegram_bot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CustomerRequest(
        @NotBlank(message = "The Name is mandatory")
        @Schema(
                description = "Name",
                example = "Vincyt"
        )
        String name,
        @NotBlank(message = "The email is mandatory")
        @Email
        String email,
        @NotBlank(message = "The Number is mandatory")
        @Pattern(
                regexp =
                        "^\\+?55[1-9]{2}(?:9[1-9]{8}|[2-5][8-9]{7}$)",
                message = "Phone number have to format international correct (+55DDNUMBER)"

        )
        String phoneNumber
) {
}
