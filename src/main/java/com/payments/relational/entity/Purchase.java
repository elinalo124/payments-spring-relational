package com.payments.relational.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    @Column(nullable=false, name = "payment_voucher")
    private String paymentVoucher;

    @Column(nullable=false)
    private String store;

    @Column(nullable=false, name = "cuit_store")
    private String cuitStore;

    @Column(nullable=false)
    private float amount;

    @Column(nullable=false, name = "final_amount")
    private float finalAmount;
}
