package com.payments.relational.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Quota> quotas;

    public void addQuota(Quota quota) { this.quotas.add(quota); }
}
