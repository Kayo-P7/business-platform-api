package com.Vy.telegram_bot.model;

import com.Vy.telegram_bot.enums.ProductCategory;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Array;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

//@Entity
@Data
@Entity
@Table(name = "tb_products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Enumerated(EnumType.STRING)
    private ProductCategory category;
    private BigDecimal price;
    private BigDecimal discountPercentage;
    private BigDecimal rating;
    private Integer stock;
    @Array(length = 10)
    private List<String> tags;
    private String brand;
    private String sku;
    private Integer weight;
    @Embedded
    private Dimensions dimensions;
    private String warrantyInformation;
    private String shippingInformation;
    private String availabilityStatus;
    @ElementCollection
    @AttributeOverride(
            name = "rating",
            column = @Column(name = "rating_review")
    )
    private List<Review> reviews;
    private String returnPolicy;
    private Integer minimumOrderQuantity;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "createdAt",
                    column = @Column(name = "created_at")
            ),
            @AttributeOverride(
                    name = "updateAt",
                    column = @Column(name = "update_at")
            ),
            @AttributeOverride(
                    name = "barcode",
                    column = @Column(name = "barcode")
            ),
            @AttributeOverride(
                    name = "qrCode",
                    column = @Column(name = "qr_code")
            )
    })
    private Meta meta = new Meta();
    @Column(columnDefinition = "TEXT[]")
    private List<String> images;
    @Column(columnDefinition = "TEXT")
    private String thumbnail;
    @Column(nullable = false)
    private Boolean active = false;

}
