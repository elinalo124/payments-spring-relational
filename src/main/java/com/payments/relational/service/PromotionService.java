package com.payments.relational.service;

import com.payments.relational.entity.Promotion;

import java.util.List;

public interface PromotionService {
    Promotion savePromotion(Long BankId, Promotion promo);
    List<Promotion> getAllPromotions();
}
