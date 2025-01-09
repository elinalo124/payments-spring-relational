package com.payments.relational.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FinancingDTO {
    private String code;
    private String promotionTitle;
    private String nameStore;
    private String cuitStore;
    private LocalDate validityStartDate;
    private LocalDate validityEndDate;
    private String comments;
    private int numberOfQuotas;
    private float interest;
}
