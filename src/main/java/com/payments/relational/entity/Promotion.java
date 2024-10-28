package com.payments.relational.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable=false)
    private String code;

    @Column(nullable=false)
    private String promotionTitle;

    @Column(nullable=false)
    private String nameStore;

    @Column(nullable=false)
    private String cuitStore;

    @Column(nullable=false)
    private LocalDate validityStartDate;

    @Column(nullable=false)
    private LocalDate validityEndDate;

    private String comments;
}
