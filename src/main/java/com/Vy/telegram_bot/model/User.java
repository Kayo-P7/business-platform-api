package com.Vy.telegram_bot.model;

import com.Vy.telegram_bot.enums.RoleStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Entity
@Table(name = "tb_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleStatus role;

    @OneToOne
    @JoinColumn(name = "customers_id", unique = true, nullable = false)
    private Customer customer;
}
