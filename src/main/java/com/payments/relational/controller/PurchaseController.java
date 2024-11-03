package com.payments.relational.controller;


import com.payments.relational.entity.Customer;
import com.payments.relational.entity.Purchase;
import com.payments.relational.entity.PurchaseMonthlyPayments;
import com.payments.relational.entity.PurchaseSinglePayment;
import com.payments.relational.service.PurchaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchasing")
public class PurchaseController {
    Logger logger = LoggerFactory.getLogger(PurchaseController.class);

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Purchase> getPurchaseById(@PathVariable Long id) {
        return purchaseService.getPurchaseById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Purchase>> getAllPurchases() {
        List<Purchase> purchases = purchaseService.getAllPurchases();
        return ResponseEntity.ok(purchases);
    }

    @PostMapping("/single")
    public ResponseEntity<Purchase> createSinglePurchase(@RequestBody PurchaseSinglePayment singlePurchase) {
        try {
            Purchase savedPurchase = purchaseService.createPurchaseSinglePayment(singlePurchase);
            return ResponseEntity.ok(savedPurchase);
        }catch (Exception e){
            logger.error("There was a error saving the purchase information", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/monthly")
    public ResponseEntity<Purchase> createMonthlyPurchase(@RequestBody PurchaseMonthlyPayments monthlyPurchase) {
        try {
            Purchase savedPurchase = purchaseService.createPurchaseMonthlyPayments(monthlyPurchase);
            return ResponseEntity.ok(savedPurchase);
        }catch (Exception e){
            logger.error("There was a error saving the purchase information", e);
            return ResponseEntity.badRequest().build();
        }
    }

}
