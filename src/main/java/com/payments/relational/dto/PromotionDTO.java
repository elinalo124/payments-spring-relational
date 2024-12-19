package com.payments.relational.dto;

import java.time.LocalDate;

public class PromotionDTO {

    private Long id;
    private String code;
    private String promotionTitle;
    private String nameStore;
    private String cuitStore;
    private LocalDate validityStartDate;
    private LocalDate validityEndDate;
    private String comments;
    private Integer numberOfQuotas;
    private Double interest;
    private Double discountPercentage;
    private Boolean onlyCash;
    private Double priceCap;
    private Long bankId;
}