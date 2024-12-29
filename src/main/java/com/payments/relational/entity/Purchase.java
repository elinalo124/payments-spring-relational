package com.payments.relational.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Purchase {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "purchase_date")
    private LocalDate purchase_date = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    @JsonManagedReference
    private Card card;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "promotion_id", referencedColumnName = "id")
    @JsonManagedReference
    private Promotion validPromotion;
}
