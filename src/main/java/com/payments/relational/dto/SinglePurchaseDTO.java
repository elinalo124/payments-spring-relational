package com.payments.relational.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SinglePurchaseDTO {
    private String paymentVoucher;
    private String store;
    private String cuitStore;
    private float amount;
    private float finalAmount;
    private Long cardId;
    private String validPromotionCode;
    private float storeDiscount;
}
