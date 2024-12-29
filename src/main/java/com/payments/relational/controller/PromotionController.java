package com.payments.relational.controller;


import com.payments.relational.entity.Discount;
import com.payments.relational.entity.Financing;
import com.payments.relational.entity.Promotion;
import com.payments.relational.service.PromotionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/promotions")
public class PromotionController {

    private final PromotionService promotionService;
    Logger logger = LoggerFactory.getLogger(PromotionController.class);


    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @GetMapping
    public ResponseEntity<List<Promotion>> getAllPromotions() {
        List<Promotion> promotions = promotionService.getAllPromotions();
        return ResponseEntity.ok(promotions);
    }

   @PostMapping("/discount")
    public ResponseEntity<Promotion> createDiscountPromotion(
            @RequestParam Long bankId,
            @RequestBody Discount promotion
    ) {
       try {
           Promotion savedPromotion = promotionService.savePromotion(bankId, promotion);
           return ResponseEntity.ok().body(savedPromotion);
       } catch (Exception e) {
           logger.error("There was a error saving the promotion information", e);
           return ResponseEntity.badRequest().build();
       }
    }

    @PostMapping("/financing")
    public ResponseEntity<Promotion> createFinancingPromotion(
            @RequestParam Long bankId,
            @RequestBody Financing promotion
    ) {
        try {
            Promotion savedPromotion = promotionService.savePromotion(bankId, promotion);
            return ResponseEntity.ok().body(savedPromotion);
        } catch (Exception e) {
            logger.error("There was a error saving the promotion information", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{promotionId}/validity")
    public ResponseEntity<Promotion> extendPromotion(
            @PathVariable Long promotionId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate extendDate
    ) {
        try {
            Promotion savedPromotion = promotionService.extendPromotion(promotionId, extendDate);
            return ResponseEntity.ok().body(savedPromotion);
        } catch (Exception e) {
            logger.error("There was a error saving the promotion information", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{promotionCode}")
    public ResponseEntity<String> removePromotion(@PathVariable String promotionCode) {
        try {
            String result = promotionService.removePromotionByCode(promotionCode);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("There was a error saving the promotion information", e);
            return ResponseEntity.badRequest().build();
        }
    }

}
