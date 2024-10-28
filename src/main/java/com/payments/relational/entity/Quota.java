package com.payments.relational.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Quota {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable=false)
    private int number;

    @Column(nullable=false)
    private float price;

    @Column(nullable=false)
    private String month;

    @Column(nullable=false)
    private String year;
}
