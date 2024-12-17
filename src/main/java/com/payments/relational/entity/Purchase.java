package com.payments.relational.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    @JsonBackReference
    private Card card;

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
