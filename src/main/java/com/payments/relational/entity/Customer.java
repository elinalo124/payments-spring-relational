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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(mappedBy = "customers")
    private Set<Bank> banks = new HashSet<>();

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
}
