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

    @Column(nullable=false, name = "promotion_title")
    private String promotionTitle;

    @Column(nullable=false, name = "name_store")
    private String nameStore;

    @Column(nullable=false, name = "cuit_store")
    private String cuitStore;

    @Column(nullable=false, name = "validity_start_date")
    private LocalDate validityStartDate;

    @Column(nullable=false, name = "validity_end_date")
    private LocalDate validityEndDate;

    private String comments;
}
