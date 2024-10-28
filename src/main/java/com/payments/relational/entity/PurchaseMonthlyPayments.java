package com.payments.relational.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseMonthlyPayments extends Purchase{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable=false)
    private float interest;

    @Column(nullable=false)
    private int numberOfQuotas;
}
