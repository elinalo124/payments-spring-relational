package com.payments.relational.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable=false)
    private String completeName;

    @Column(nullable=false)
    private String dni;

    @Column(nullable=false)
    private String cuil;

    @Column(nullable=false)
    private String address;

    @Column(nullable=false)
    private String telephone;

    @Column(nullable=false)
    private LocalDate entryDate;
}
