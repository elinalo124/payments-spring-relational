package com.payments.relational.service;

import com.payments.relational.entity.*;
import com.payments.relational.exception.PaymentsException;
import com.payments.relational.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PaymentSummaryServiceImpl implements PaymentSummaryService {
    private PurchaseSinglePaymentRepository singlePaymentRepository;
    private PurchaseMonthlyPaymentsRepository monthlyPaymentsRepository;
    private PurchaseRepository purchaseRepository;
    private CardRepository cardRepository;
    private PaymentSummaryRepository paymentSummaryRepository;

    @Autowired
    public PaymentSummaryServiceImpl(
            PurchaseSinglePaymentRepository singlePaymentRepository,
            PurchaseMonthlyPaymentsRepository monthlyPaymentsRepository,
            PurchaseRepository purchaseRepository,
            CardRepository cardRepository,
            PaymentSummaryRepository paymentSummaryRepository
    ){
        this.singlePaymentRepository = singlePaymentRepository;
        this.monthlyPaymentsRepository = monthlyPaymentsRepository;
        this.purchaseRepository = purchaseRepository;
        this.cardRepository = cardRepository;
        this.paymentSummaryRepository = paymentSummaryRepository;
    }

    @Override
    public PaymentSummary getPaymentSummary(Long cardId, int month, int year) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new PaymentsException("Card not found for ID: " + cardId));

        List<PurchaseMonthlyPayments> monthlyPayments = purchaseRepository
                .findMonthlyPaymentsByCardAndDate(cardId, month, year);

        List<PurchaseSinglePayment> singlePayments = purchaseRepository
                .findSinglePaymentsByCardAndDate(cardId, month, year);

        float totalPrice = calculateTotalPrice(monthlyPayments, singlePayments);
        LocalDate firstExpiration = LocalDate.of(year, month, 1).plusMonths(1);
        LocalDate secondExpiration = firstExpiration.withDayOfMonth(15);

        PaymentSummary summary = new PaymentSummary();
        summary.setCode(UUID.randomUUID().toString());
        summary.setMonthh(String.valueOf(month));
        summary.setYearr(String.valueOf(year));
        summary.setFirstExpiration(firstExpiration);
        summary.setSecondExpiration(secondExpiration);
        summary.setSurchargePercentage(5.0f);
        summary.setTotalPrice(totalPrice);
        summary.setCard(card);
        summary.setQuotasPayments(collectQuotasFromMonthlyPayments(monthlyPayments));
        summary.setCashPayments(new HashSet<>(singlePayments));

        return paymentSummaryRepository.save(summary);


    }

    private Set<Quota> collectQuotasFromMonthlyPayments(List<PurchaseMonthlyPayments> monthlyPayments) {
        return monthlyPayments.stream()
                .flatMap(p -> p.getQuotas().stream())
                .collect(Collectors.toSet());
    }

    private float calculateTotalPrice(List<PurchaseMonthlyPayments> monthlyPayments,
                                      List<PurchaseSinglePayment> singlePayments) {
        float monthlyTotal = (float) monthlyPayments.stream()
                .flatMap(p -> p.getQuotas().stream())
                .mapToDouble(Quota::getPrice)
                .sum();

        float singleTotal = (float) singlePayments.stream()
                .mapToDouble(PurchaseSinglePayment::getFinalAmount)
                .sum();

        return (monthlyTotal + singleTotal);
    }

    private String getPaymentSummaryCode(String cardNumber, int month, int year) {
        String lastThreeNumbers = cardNumber.substring(cardNumber.length() - 3);
        String yearString = String.valueOf(year % 100);
        String montString = String.valueOf(month);
        return lastThreeNumbers + montString + yearString;
    }

    private float calculateTotalPrice(Set<Purchase> purchases) {
        if (purchases == null || purchases.isEmpty()) {
            return 0f;
        }

        return (float) purchases.stream()
                .mapToDouble(Purchase::getFinalAmount)
                .sum();
    }

    private float calculateQuotaTotalPrice(Set<Quota> quotas) {
        if (quotas == null || quotas.isEmpty()) {
            return 0f;
        }

        return (float) quotas.stream()
                .mapToDouble(Quota::getPrice)
                .sum();
    }
}
