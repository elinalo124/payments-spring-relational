package com.payments.relational.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable=false, name = "complete_name")
    private String completeName;

    @Column(nullable=false, name = "dni")
    private String dni;

    @Column(nullable=false, name = "cuil")
    private String cuil;

    @Column(nullable=false, name = "address")
    private String address;

    @Column(nullable=false, name = "telephone")
    private String telephone;

    @Column(nullable=false, name = "entry_date")
    private LocalDate entryDate;

    @ManyToMany(mappedBy = "customers")
    private Set<Bank> banks = new HashSet<>();

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Card> cards;

    public void addBank(Bank bank) {
        this.banks.add(bank);
    }

    public Set<Bank> getBanks() {
        return banks;
    }
}
