package com.payments.relational.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "bank_customer",
            joinColumns = @JoinColumn(name = "bank_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id")
    )
    @JsonManagedReference
    private Set<Customer> members;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Set<Promotion> promotions;

    public void addCustomer(Customer customer) {
        this.members.add(customer);
    }

    public void addPromo(Promotion promo) {
        this.promotions.add(promo);
    }
}
