package com.payments.relational.service;

import com.payments.relational.dto.MonthlyPaymentPurchaseDTO;
import com.payments.relational.dto.SinglePurchaseDTO;
import com.payments.relational.entity.Purchase;
import com.payments.relational.entity.PurchaseMonthlyPayments;
import com.payments.relational.entity.PurchaseSinglePayment;

import java.util.List;
import java.util.Map;

public interface PurchaseService {
    List<Purchase> getAllPurchases();
    Purchase getPurchaseById(Long id);
    Purchase createPurchase(Purchase purchase);
    PurchaseMonthlyPayments createPurchaseMonthlyPayments(MonthlyPaymentPurchaseDTO monthlyPurchase);
    PurchaseSinglePayment createPurchaseSinglePayment(SinglePurchaseDTO singlePurchase);
    Map<String, Object> getTopStore(int month, int year);
}
