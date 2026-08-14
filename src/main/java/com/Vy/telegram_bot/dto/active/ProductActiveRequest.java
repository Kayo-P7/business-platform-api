package com.Vy.telegram_bot.dto.active;

import jakarta.validation.constraints.NotNull;

public record ProductActiveRequest(
        @NotNull
        Boolean active
) {

}
