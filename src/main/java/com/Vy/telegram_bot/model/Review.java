package com.Vy.telegram_bot.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable

public class Review {

   @Min(0)
   @Max(5)
   private BigDecimal rating;
   private String comment;
   private Instant date;
   private String reviewerName;
   private String reviewerEmail;
}
