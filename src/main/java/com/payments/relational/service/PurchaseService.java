package com.payments.relational.service;

import com.payments.relational.dto.MonthlyPaymentPurchaseDTO;
import com.payments.relational.dto.SinglePurchaseDTO;
import com.payments.relational.entity.Purchase;
import com.payments.relational.entity.PurchaseMonthlyPayments;
import com.payments.relational.entity.PurchaseSinglePayment;
import com.payments.relational.exception.PaymentsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface PurchaseService {
    List<Purchase> getAllPurchases();
    Optional<Purchase> getPurchaseById(Long id);
    Purchase createPurchase(Purchase purchase) throws PaymentsException;
    PurchaseMonthlyPayments createPurchaseMonthlyPayments(MonthlyPaymentPurchaseDTO monthlyPurchase);
    PurchaseSinglePayment createPurchaseSinglePayment(SinglePurchaseDTO singlePurchase);
}
