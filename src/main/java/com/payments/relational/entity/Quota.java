package com.payments.relational.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@EqualsAndHashCode(exclude="purchase")
public class Quota {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private int number;

    @Column(nullable=false)
    private float price;

    @Column(nullable=false)
    private String monthh;

    @Column(nullable=false)
    private String yearr;

    @ManyToOne
    @JoinColumn(name = "purchase_monthly_payments_id", referencedColumnName = "id")
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private PurchaseMonthlyPayments purchase;

    public void setPurchaseMonthlyPayments(PurchaseMonthlyPayments purchaseMonthlyPayments) {
        this.purchase = purchaseMonthlyPayments;
    }
}
