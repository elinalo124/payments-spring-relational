package com.payments.relational.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
