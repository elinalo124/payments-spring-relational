package com.payments.relational.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(name = "payment_voucher", nullable=false)
    private String paymentVoucher;

    @Column(nullable=false)
    private String store;

    @Column(name = "cuit_store", nullable=false)
    private String cuitStore;

    @Column(nullable=false)
    private float amount;

    @Column(name = "final_amount", nullable=false)
    private float finalAmount;

    @ManyToOne
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    @JsonManagedReference
    private Card card;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "promotion_id", referencedColumnName = "id")
    @JsonManagedReference
    private Promotion validPromotion;
}
