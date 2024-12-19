package com.payments.relational.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("DISCOUNT")
public class Discount extends Promotion{
    @Column(name = "discount_percentage")
    private float discountPercentage;

    @Column(name = "price_cap")
    private float priceCap;

    @Column(name = "only_cash")
    private boolean onlyCash;
}
