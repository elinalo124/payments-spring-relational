package com.payments.relational.service;

import com.payments.relational.entity.*;
import com.payments.relational.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PaymentSummaryServiceImpl implements PaymentSummaryService {
    private PurchaseSinglePaymentRepository singlePaymentRepository;
    private PurchaseMonthlyPaymentsRepository monthlyPaymentsRepository;
    private CardRepository cardRepository;

    @Autowired
    public PaymentSummaryServiceImpl(
            PurchaseSinglePaymentRepository singlePaymentRepository,
            PurchaseMonthlyPaymentsRepository monthlyPaymentsRepository,
            CardRepository cardRepository
    ){
        this.singlePaymentRepository = singlePaymentRepository;
        this.monthlyPaymentsRepository = monthlyPaymentsRepository;
        this.cardRepository = cardRepository;
    }

    @Override
    public PaymentSummary getPaymentSummary(Long cardId, int month, int year) {
        Optional<Card> cardOptional = cardRepository.findById(cardId);
        if (cardOptional.isPresent()) {
            Set<PurchaseSinglePayment> singlePaymentsList = singlePaymentRepository.findByCardAndMonthAndYear(cardId,month, year);
            Set<PurchaseMonthlyPayments> monthlyPaymentsList = monthlyPaymentsRepository.findByCardAndMonthAndYear(cardId,month, year);
            Set<Purchase> purchases = new HashSet<>();
            purchases.addAll(singlePaymentsList);
            purchases.addAll(monthlyPaymentsList);

            PaymentSummary paymentSummary = new PaymentSummary();
            paymentSummary.setTotalPrice(calculateTotalPrice(purchases));
            paymentSummary.setCard(cardOptional.get());
            paymentSummary.setCashPayments(singlePaymentsList);
            paymentSummary.setQuotasPayments(monthlyPaymentsList);
        }

        return null;
    }

    private float calculateTotalPrice(Set<Purchase> purchases) {
        if (purchases == null || purchases.isEmpty()) {
            return 0f;
        }

        return (float) purchases.stream()
                .mapToDouble(Purchase::getFinalAmount)
                .sum();
    }
}
