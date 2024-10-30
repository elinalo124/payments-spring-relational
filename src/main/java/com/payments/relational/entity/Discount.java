package com.payments.relational.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Discount extends Promotion{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable=false, name = "discount_percentage")
    private float discountPercentage;

    @Column(nullable=false, name = "price_cap")
    private float priceCap;

    @Column(nullable=false, name = "only_cash")
    private boolean onlyCash;
}
