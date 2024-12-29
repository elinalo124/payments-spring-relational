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
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "complete_name")
    private String completeName;

    @Column(name = "dni")
    private String dni;

    @Column(nullable=false, name = "cuil")
    private String cuil;

    @Column(name = "address")
    private String address;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "entry_date")
    private LocalDate entryDate;

    @ManyToMany(mappedBy = "members")
    @JsonBackReference
    private Set<Bank> banks;
}
