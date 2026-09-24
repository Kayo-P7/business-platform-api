package com.Vy.telegram_bot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Dimensions {

    @NotNull
    private BigDecimal width;
    @NotNull
    private BigDecimal height;
    @NotNull
    private BigDecimal depth;
}
