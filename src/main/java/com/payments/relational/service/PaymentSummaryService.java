package com.payments.relational.service;

import com.payments.relational.entity.PaymentSummary;

public interface PaymentSummaryService {
    PaymentSummary getPaymentSummary(Long cardId, int month, int year);
}
