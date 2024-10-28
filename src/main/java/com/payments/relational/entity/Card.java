package com.payments.relational.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable=false)
    private String number;

    @Column(nullable=false)
    private String ccv;

    @Column(nullable=false)
    private String cardHolderNameInCard;

    @Column(nullable=false)
    private LocalDate since;

    @Column(nullable=false)
    private LocalDate expirationDate;
}
