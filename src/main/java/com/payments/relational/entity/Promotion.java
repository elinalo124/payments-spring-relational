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
@Inheritance(strategy = InheritanceType.JOINED)
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @Column(nullable=false)
    private String code;

    @Column(nullable=false, name = "promotion_title")
    private String promotionTitle;

    @Column(nullable=false, name = "name_store")
    private String nameStore;

    @Column(nullable=false, name = "cuit_store")
    private String cuitStore;

    @Column(nullable=false, name = "validity_start_date")
    private LocalDate validityStartDate;

    @Column(nullable=false, name = "validity_end_date")
    private LocalDate validityEndDate;

    private String comments;
}
