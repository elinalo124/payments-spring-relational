package com.payments.relational.service;

import com.payments.relational.entity.Promotion;

import java.time.LocalDate;
import java.util.List;

public interface PromotionService{
    List<Promotion> getAllPromotions();
    Promotion savePromotion(Long bankId, Promotion promo);
    Promotion extendPromotion(Long promoId, LocalDate newDate);
    String removePromotionByCode (String promotionCode);
    List<Promotion> getValidPromotionsInRange(String cuitStore, LocalDate validityStartDate, LocalDate validityEndDate);
    Promotion getMostUsedPromotion();
}
