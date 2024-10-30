package com.payments.relational.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;

    @OneToMany(mappedBy = "paymentSummary", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Quota> quotas;

    @OneToMany(mappedBy = "paymentSummary", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PurchaseSinglePayment> singlePayments;

    @Column(nullable=false)
    private String code;

    @Column(nullable=false)
    private String month;

    @Column(nullable=false)
    private String year;

    @Column(nullable=false, name = "first_expiration")
    private LocalDate firstExpiration;

    @Column(nullable=false, name = "second_expiration")
    private LocalDate secondExpiration;

    @Column(nullable=false, name = "surcharge_percentage")
    private float surchargePercentage;

    @Column(nullable=false, name = "total_price")
    private float totalPrice;
}
