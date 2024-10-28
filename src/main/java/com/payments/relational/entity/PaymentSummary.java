package com.payments.relational.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable=false)
    private String code;

    @Column(nullable=false)
    private String month;

    @Column(nullable=false)
    private String year;

    @Column(nullable=false)
    private LocalDate firstExpiration;

    @Column(nullable=false)
    private LocalDate secondExpiration;

    @Column(nullable=false)
    private float surchargePercentage;

    @Column(nullable=false)
    private float totalPrice;
}
