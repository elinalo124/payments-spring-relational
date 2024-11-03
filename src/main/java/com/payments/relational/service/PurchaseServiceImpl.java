package com.payments.relational.service;

import com.payments.relational.entity.Card;
import com.payments.relational.entity.Purchase;
import com.payments.relational.entity.PurchaseMonthlyPayments;
import com.payments.relational.entity.PurchaseSinglePayment;
import com.payments.relational.exception.PaymentsException;
import com.payments.relational.repository.CardRepository;
import com.payments.relational.repository.PurchaseMonthlyPaymentsRepository;
import com.payments.relational.repository.PurchaseRepository;
import com.payments.relational.repository.PurchaseSinglePaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseServiceImpl implements PurchaseService{
    private final PurchaseRepository purchaseRepository;
    private final PurchaseMonthlyPaymentsRepository purchaseMonthlyPaymentsRepository;
    private final PurchaseSinglePaymentRepository purchaseSinglePaymentRepository;
    private final CardRepository cardRepository;

    @Autowired
    public PurchaseServiceImpl(
            PurchaseRepository purchaseRepository,
            PurchaseMonthlyPaymentsRepository purchaseMonthlyPaymentsRepository,
            PurchaseSinglePaymentRepository purchaseSinglePaymentRepository,
            CardRepository cardRepository
    ) {
        this.purchaseRepository = purchaseRepository;
        this.purchaseMonthlyPaymentsRepository = purchaseMonthlyPaymentsRepository;
        this.purchaseSinglePaymentRepository = purchaseSinglePaymentRepository;
        this.cardRepository = cardRepository;
    }

    @Override
    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    @Override
    public Optional<Purchase> getPurchaseById(Long id) {
        return purchaseRepository.findById(id);
    }

    @Override
    public Purchase createPurchase(Purchase purchase) throws PaymentsException {
        Optional<Card> cardOptional = cardRepository.findById(purchase.getCard().getId());
        if (cardOptional.isPresent()) {
            return purchaseRepository.save(purchase);
        } else {
            throw new PaymentsException("There's an error with the purchase card");
        }
    }

    @Override
    public PurchaseMonthlyPayments createPurchaseMonthlyPayments(PurchaseMonthlyPayments monthlyPurchase) throws PaymentsException {
        Optional<Card> cardOptional = cardRepository.findById(monthlyPurchase.getCard().getId());
        if (cardOptional.isPresent()) {
            return purchaseMonthlyPaymentsRepository.save(monthlyPurchase);
        } else {
            throw new PaymentsException("There's an error with the purchase card");
        }
    }

    @Override
    public PurchaseSinglePayment createPurchaseSinglePayment(PurchaseSinglePayment singlePurchase) throws PaymentsException {
        Optional<Card> cardOptional = cardRepository.findById(singlePurchase.getCard().getId());
        if (cardOptional.isPresent()) {
            return purchaseSinglePaymentRepository.save(singlePurchase);
        } else {
            throw new PaymentsException("There's an error with the purchase card");
        }
    }
}
