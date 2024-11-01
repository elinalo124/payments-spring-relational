package com.payments.relational.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bank")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Long id;

    @JsonManagedReference
    @ManyToMany(mappedBy = "banks")
    private Set<Customer> customers = new HashSet<>();

    @OneToMany(mappedBy = "bank", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Card> cards;

    @OneToMany(mappedBy = "bank", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Promotion> promotions;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private String cuit;

    @Column(nullable=false)
    private String address;

    @Column(nullable=false)
    private String telephone;

    public void addCustomer(Customer customer) {
        this.customers.add(customer);
    }
}
