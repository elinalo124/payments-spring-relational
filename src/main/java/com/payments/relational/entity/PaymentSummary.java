package com.payments.relational.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payment_summary")
public class PaymentSummary {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String code;

    @Column(nullable=false)
    private String monthh;

    @Column(nullable=false)
    private String yearr;

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

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Quota>  quotasPayments;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<PurchaseSinglePayment> cashPayments;
}
