package com.Vy.telegram_bot.model;

import com.Vy.telegram_bot.model.pk.PkOrderAndProduct;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_orders_products")
public class OrderItem {

    @EmbeddedId
    private PkOrderAndProduct pkOrderAndProduct = new PkOrderAndProduct();

    @ManyToOne
    @JoinColumn(name = "id_product")
    @MapsId("productId")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "id_order")
    @MapsId("orderId")
    private Order order;
    private Integer quantity;
    private BigDecimal price;

    public OrderItem(Product product, Order order, Integer quantity, BigDecimal price) {
        setOrder(order);
        setProduct(product);
        this.product = product;
        this.order = order;
        this.quantity = quantity;
        this.price = price;

    }

    public void setOrder(Order order) {
        this.order = order;
        if (order != null) {
            this.pkOrderAndProduct.setOrderId(order.getId());
        }
    }

    public void setProduct(Product product) {
        this.product = product;
        if (product != null) {
            this.pkOrderAndProduct.setProductId(product.getId());
        }
    }

    public BigDecimal getTotal() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}
