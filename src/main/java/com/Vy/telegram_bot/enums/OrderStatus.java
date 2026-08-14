package com.Vy.telegram_bot.enums;

import java.util.Arrays;

public enum OrderStatus {
    PENDING(1),
    PAID(2),
    SHIPPED(3),
    DELIVERED(4),
    CANCELED(5);

    int position;

    OrderStatus(int position) {
        this.position = position;
    }

    public int getPositon() {
        return position;
    }
}



