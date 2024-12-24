package com.payments.relational.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseMonthlyPayments extends Purchase{
    @Column(nullable = false)
    private float interest;

    @Column(name = "number_of_quotas", nullable = false)
    private int numberOfQuotas;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "purchase_monthly_payments_id", referencedColumnName = "id")
    private Set<Quota> quotas;
}
