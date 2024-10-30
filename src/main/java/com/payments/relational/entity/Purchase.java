package com.payments.relational.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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
