package com.payments.relational.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String paymentVoucher;
    private String store;
    private String cuitStore;
    private float amount;
    private float finalAmount;
}
