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

    @Column(nullable=false)
    private float discountPercentage;

    @Column(nullable=false)
    private float priceCap;

    @Column(nullable=false)
    private boolean onlyCash;
}
