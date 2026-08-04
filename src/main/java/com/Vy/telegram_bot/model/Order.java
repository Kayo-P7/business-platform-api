package com.Vy.telegram_bot.model;

import com.Vy.telegram_bot.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private OrderStatus orderStatus;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    List<OrderItem> items = new ArrayList<>();

    public BigDecimal getTotal() {

      BigDecimal total = BigDecimal.ZERO;

        for (OrderItem item : items) {
            total = total.add(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }
        return total;
    }
}
