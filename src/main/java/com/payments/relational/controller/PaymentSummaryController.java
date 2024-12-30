package com.payments.relational.controller;

import com.payments.relational.entity.PaymentSummary;
import com.payments.relational.service.PaymentSummaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paymentSummary")
public class PaymentSummaryController {

    Logger logger = LoggerFactory.getLogger(PaymentSummaryController.class);

    private PaymentSummaryService paymentSummaryService;

    @Autowired
    public PaymentSummaryController(PaymentSummaryService paymentSummaryService) { this.paymentSummaryService = paymentSummaryService; }

    // 3) Generar el total de pago de un mes dado, informando las compras correspondientes
    @GetMapping
    public ResponseEntity<PaymentSummary> getPaymentSummary(
            @RequestParam Long cardId,
            @RequestParam int month,
            @RequestParam int year
    ) {
        try {
            PaymentSummary paymentSummary = paymentSummaryService.getPaymentSummary(cardId, month, year);
            return ResponseEntity.ok(paymentSummary);
        }catch (Exception e){
            logger.error("There was a error getting the payment summary information", e);
            return ResponseEntity.badRequest().build();
        }
    }

}
