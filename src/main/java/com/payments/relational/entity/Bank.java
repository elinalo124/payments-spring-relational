package com.payments.relational.entity;


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

    @ManyToMany
    @JoinTable(
            name = "customer_bank",
            joinColumns = @JoinColumn(name = "bank_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    private Set<Customer> customers = new HashSet<>();

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
