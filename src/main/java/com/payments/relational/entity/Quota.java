package com.payments.relational.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude="purchase")
public class Quota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private int number;

    @Column(nullable=false)
    private float price;

    @Column(nullable=false)
    private String month;

    @Column(nullable=false)
    private String year;

    @ManyToOne
    @JoinColumn(name = "purchase_monthly_payments_id", referencedColumnName = "id")
    @JsonBackReference
    private PurchaseMonthlyPayments purchase;

    public void setPurchaseMonthlyPayments(PurchaseMonthlyPayments purchaseMonthlyPayments) {
        this.purchase = purchaseMonthlyPayments;
    }
}
