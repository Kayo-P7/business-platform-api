package com.Vy.telegram_bot.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

//@Entity
@Data
@Entity
@Table(name = "tb_products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    @Column(name = "created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/yyyy/MM'T'HH:mm:ss'Z'", timezone = "GMT")
    private LocalDateTime createdAt; //Data de criação
    @Column(nullable = false)
    private Boolean active = false; //produto ativo ou desativado
}
