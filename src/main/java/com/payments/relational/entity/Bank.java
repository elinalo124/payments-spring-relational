package com.payments.relational.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bank")
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "cuit")
    private String cuit;

    @Column(name = "address")
    private String address;

    @Column(name = "telephone")
    private String telephone;

    @ManyToMany(mappedBy = "banks")
    private Set<Customer> members = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Set<Promotion> promotions;
}
