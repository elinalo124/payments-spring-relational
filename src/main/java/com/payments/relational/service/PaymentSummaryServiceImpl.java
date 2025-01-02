package com.payments.relational.service;

import com.payments.relational.entity.*;
import com.payments.relational.exception.PaymentsException;
import com.payments.relational.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;

@Service
public class PaymentSummaryServiceImpl implements PaymentSummaryService {
    private PurchaseRepository purchaseRepository;
    private CardRepository cardRepository;
    private PaymentSummaryRepository paymentSummaryRepository;
    private QuotaRepository quotaRepository;

    @Autowired
    public PaymentSummaryServiceImpl(
            PurchaseRepository purchaseRepository,
            CardRepository cardRepository,
            PaymentSummaryRepository paymentSummaryRepository,
            QuotaRepository quotaRepository
    ){
        this.purchaseRepository = purchaseRepository;
        this.cardRepository = cardRepository;
        this.paymentSummaryRepository = paymentSummaryRepository;
        this.quotaRepository = quotaRepository;
    }

    @Override
    public PaymentSummary getPaymentSummary(Long cardId, int month, int year) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new PaymentsException("Card not found for ID: " + cardId));

        List<Quota> monthlyPayments = quotaRepository
                .findQuotasByMonthYearAndCardId(month, year, cardId);

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
        summary.setQuotasPayments(new HashSet<>(monthlyPayments));
        summary.setCashPayments(new HashSet<>(singlePayments));

        return paymentSummaryRepository.save(summary);
    }

    private float calculateTotalPrice(List<Quota> quotas,
                                      List<PurchaseSinglePayment> singlePayments) {
        float monthlyTotal = (float) quotas.stream()
                .mapToDouble(Quota::getPrice)
                .sum();

        float singleTotal = (float) singlePayments.stream()
                .mapToDouble(PurchaseSinglePayment::getFinalAmount)
                .sum();

        return (monthlyTotal + singleTotal);
    }
}
