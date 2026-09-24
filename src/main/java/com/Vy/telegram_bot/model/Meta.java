package com.Vy.telegram_bot.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Meta {
    @NotNull
    private Instant createdAt;
    @NotNull
    private Instant updateAt;
    @NotBlank
    private String barcode;
    @NotBlank
    private String qrCode;
}
