package com.payments.relational.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "bank")
public class Bank {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NonNull
    private String name;

    @Column(name = "cuit")
    @NonNull
    private String cuit;

    @Column(name = "address")
    @NonNull
    private String address;

    @Column(name = "telephone")
    @NonNull
    private String telephone;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "bank_customer",
            joinColumns = @JoinColumn(name = "bank_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id")
    )
    @JsonManagedReference
    private Set<Customer> members = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Set<Promotion> promotions;

    public void addCustomer(Customer customer) {
        this.members.add(customer);
    }

    public void addPromo(Promotion promo) {
        this.promotions.add(promo);
    }
}
