package com.payments.relational.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "card", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Purchase> purchases;

    @OneToMany(mappedBy = "card", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PaymentSummary> paymentSummaries;

    @Column(nullable=false)
    private String number;

    @Column(nullable=false)
    private String ccv;

    @Column(nullable=false, name = "cardholder_name_in_card")
    private String cardHolderNameInCard;

    @Column(nullable=false)
    private LocalDate since;

    @Column(nullable=false, name = "expiration_date")
    private LocalDate expirationDate;

    public Customer getCustomer() {
        return this.customer;
    }

    public Bank getBank() {
        return this.bank;
    }
}
