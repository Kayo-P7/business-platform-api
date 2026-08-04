package com.Vy.telegram_bot.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "name", nullable = false)
    @NotBlank(message = "The name is mandatory")
    private String name;
    @Column(name = "email", nullable = false, unique = true)
    @NotBlank(message = "The email is mandatory")
    @Email
    private String email;
    @Column(name = "phone_number", nullable = false, unique = true)
    @NotBlank(message = "The Number is mandatory")
    @Pattern(
            regexp =
                    "^\\+?55[1-9]{2}(?:9[1-9]{8}|[2-5][8-9]{7}$)",
            message = "Phone number have to format international correct (+55DDNUMBER)"

    )
    private String phoneNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/yyyy/MM'T'HH:mm:ss'Z'", timezone = "GMT")
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private Boolean active = false;

    @OneToMany(mappedBy = "customer")
    private List<Order> order;
}
