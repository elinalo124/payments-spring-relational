package com.payments.relational.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonBackReference
    @ManyToMany
    private Set<Bank> banks = new HashSet<>();

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Card> cards;

    @Column(nullable=false, name = "complete_name")
    private String completeName;

    @Column(nullable=false)
    private String dni;

    @Column(nullable=false)
    private String cuil;

    @Column(nullable=false)
    private String address;

    @Column(nullable=false)
    private String telephone;

    @Column(nullable=false, name = "entry_date")
    private LocalDate entryDate;

    public void addBank(Bank bank) {
        this.banks.add(bank);
    }

    public Long getId() { return this.id; }

    public Set<Bank> getBanks() {
        return banks;
    }
}
