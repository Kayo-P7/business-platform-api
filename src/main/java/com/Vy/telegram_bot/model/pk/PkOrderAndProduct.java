package com.Vy.telegram_bot.model.pk;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class PkOrderAndProduct implements Serializable {
    private static final long serialVersionUID = 1L;


    private UUID productId;
    private UUID orderId;


    // O JPA precisa dos getters e setters para ler e escrever


    public PkOrderAndProduct() {
    }

    public PkOrderAndProduct(UUID productId, UUID orderId) {
        this.productId = productId;
        this.orderId = orderId;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PkOrderAndProduct that = (PkOrderAndProduct) o;
        return Objects.equals(productId, that.productId) && Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, orderId);
    }
}
