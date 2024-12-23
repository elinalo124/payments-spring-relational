package com.payments.relational.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseSinglePayment extends Purchase{
    @Column(name = "store_discount")
    private float storeDiscount;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private PaymentSummary cashPayments;
}
