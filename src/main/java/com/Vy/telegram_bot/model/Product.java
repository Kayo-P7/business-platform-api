package com.Vy.telegram_bot.model;

import jakarta.persistence.Entity;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

//@Entity
@Data
@Entity
public class Product {

    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private LocalDateTime createdAt; //Data de criação
    private Boolean active; //produto ativo ou desativado
}
