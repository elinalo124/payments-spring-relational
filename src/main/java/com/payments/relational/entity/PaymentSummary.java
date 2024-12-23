package com.payments.relational.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
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

    @Column(name = "first_expiration")
    private LocalDate firstExpiration;

    @Column(name = "second_expiration")
    private LocalDate secondExpiration;

    @Column(name = "surcharge_percentage")
    private float surchargePercentage;

    @Column(name = "total_price")
    private float totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    private Card card;
}
