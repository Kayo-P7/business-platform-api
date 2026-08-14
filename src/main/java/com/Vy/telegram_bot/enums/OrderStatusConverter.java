package com.Vy.telegram_bot.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class OrderStatusConverter implements AttributeConverter<OrderStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(OrderStatus status) {
        if (status == null) {
            return null;
        }
        return status.getPositon();
    }


    @Override
    public OrderStatus convertToEntityAttribute(Integer value) {
        if (value == null) {
            return null;
        }

        for (OrderStatus status : OrderStatus.values()) {
            if (status.getPositon() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Status invalid: " + value);
    }
}
