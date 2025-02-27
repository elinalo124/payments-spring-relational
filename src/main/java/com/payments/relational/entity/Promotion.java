package com.payments.relational.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Promotion {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(name = "promotion_title", nullable = false)
    private String promotionTitle;

    @Column(name = "name_store", nullable = false)
    private String nameStore;

    @Column(name = "cuit_store", nullable = false)
    private String cuitStore;

    @Column(name = "validity_start_date", nullable = false)
    private LocalDate validityStartDate;

    @Column(name = "validity_end_date", nullable = false)
    private LocalDate validityEndDate;

    @Column
    private String comments;

    @OneToMany(mappedBy = "validPromotion")
    @JsonBackReference
    private Set<Purchase> purchases;

    public void addPurchase(Purchase purchase) {
        this.purchases.add(purchase);
    }
}

