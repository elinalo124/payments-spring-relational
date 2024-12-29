package com.payments.relational.service;

import com.payments.relational.entity.*;
import com.payments.relational.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class PaymentSummaryServiceImpl implements PaymentSummaryService {
    private PurchaseSinglePaymentRepository singlePaymentRepository;
    private PurchaseMonthlyPaymentsRepository monthlyPaymentsRepository;
    private CardRepository cardRepository;
    private PaymentSummaryRepository paymentSummaryRepository;

    @Autowired
    public PaymentSummaryServiceImpl(
            PurchaseSinglePaymentRepository singlePaymentRepository,
            PurchaseMonthlyPaymentsRepository monthlyPaymentsRepository,
            CardRepository cardRepository,
            PaymentSummaryRepository paymentSummaryRepository
    ){
        this.singlePaymentRepository = singlePaymentRepository;
        this.monthlyPaymentsRepository = monthlyPaymentsRepository;
        this.cardRepository = cardRepository;
        this.paymentSummaryRepository = paymentSummaryRepository;
    }

    @Override
    public PaymentSummary getPaymentSummary(Long cardId, int month, int year) {
        Optional<Card> cardOptional = cardRepository.findById(cardId);
        if (cardOptional.isPresent()) {
            Card card = cardOptional.get();
            Set<PurchaseSinglePayment> singlePaymentsList = singlePaymentRepository.findByCardAndMonthAndYear(cardId,month, year);
            Set<Quota> quotaSet = monthlyPaymentsRepository.findQuotasByCardAndDate(cardId,month, year);
            Set<Purchase> purchases = new HashSet<>();
            purchases.addAll(singlePaymentsList);

            float totalPrice = calculateTotalPrice(purchases) + calculateQuotaTotalPrice(quotaSet);
            LocalDate firstExpiration = LocalDate.of(year, month, 1).plusMonths(1);
            LocalDate secondExpiration = firstExpiration.withDayOfMonth(15);

            PaymentSummary paymentSummary = new PaymentSummary();
            paymentSummary.setFirstExpiration(firstExpiration);
            paymentSummary.setSecondExpiration(secondExpiration);
            paymentSummary.setMonth(String.valueOf(month));
            paymentSummary.setYear(String.valueOf(year));
            paymentSummary.setSurchargePercentage((float) (totalPrice * 0.1));
            paymentSummary.setCode(getPaymentSummaryCode(card.getCardNumber(), month, year));
            paymentSummary.setTotalPrice(totalPrice);
            paymentSummary.setCard(card);
            paymentSummary.setCashPayments(singlePaymentsList);
            paymentSummary.setQuotasPayments(quotaSet);

            return paymentSummaryRepository.save(paymentSummary);
        }

        return null;
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
