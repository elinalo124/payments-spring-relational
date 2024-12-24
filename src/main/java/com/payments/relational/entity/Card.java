package com.payments.relational.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "card_number", nullable = false, unique = true)
    private String cardNumber;

    @Column(nullable = false, length = 3)
    private String cvv;

    @Column(name = "card_holder_name", nullable = false)
    private String cardHolderNameInCard;

    @Column(name = "since_date", nullable = false)
    private LocalDate sinceDate;

    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Bank bank;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Customer cardHolder;

    @OneToMany(mappedBy = "card")
    private Set<Purchase> purchases;
}
