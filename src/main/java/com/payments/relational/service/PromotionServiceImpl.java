package com.payments.relational.service;

import com.payments.relational.entity.Bank;
import com.payments.relational.entity.Promotion;
import com.payments.relational.entity.Purchase;
import com.payments.relational.exception.PaymentsException;
import com.payments.relational.repository.BankRepository;
import com.payments.relational.repository.PromotionRepository;
import com.payments.relational.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PromotionServiceImpl implements PromotionService {
    private final PromotionRepository promotionRepository;
    private final BankRepository bankRepository;
    private final PurchaseRepository purchaseRepository;

    @Autowired
    public PromotionServiceImpl(
            PromotionRepository promotionRepository,
            BankRepository bankRepository,
            PurchaseRepository purchaseRepository
    ) {
        this.promotionRepository = promotionRepository;
        this.bankRepository = bankRepository;
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }

    @Override
    public Promotion savePromotion(Long bankId, Promotion promo) {
        Optional<Bank> bankOptional= bankRepository.findById(bankId);
         if (bankOptional.isPresent()) {
             Bank bank = bankOptional.get();
             bank.addPromo(promo);
             return promotionRepository.save(promo);
        } else {
             throw new PaymentsException("There is no bank with the Id given");
         }
    }

    @Override
     public Promotion extendPromotion(Long promoId, LocalDate newDate) {
        Optional<Promotion> optionalPromo = promotionRepository.findById(promoId);
        if (optionalPromo.isPresent()) {
            Promotion promo = optionalPromo.get();
            if(promo.getValidityStartDate().isBefore(newDate)) {
                promo.setValidityEndDate(newDate);
                return promotionRepository.save(promo);
            } else {
                throw new PaymentsException("The new date is not valid");
            }

        }else {
            throw new PaymentsException("The promotion is not found");
        }
    }

    @Override
    public String removePromotionByCode(String promotionCode) {
        Optional<Promotion> promotionOptional = promotionRepository.findByCode(promotionCode);
        if (promotionOptional.isPresent()) {
            Promotion promo = promotionOptional.get();
            List<Purchase> purchases = purchaseRepository.findByValidPromotion(promo);
            for (Purchase purchase : purchases) {
                purchase.setValidPromotion(null);
                purchaseRepository.save(purchase);
            }
            promotionRepository.delete(promo);

            return "Promotion with code " + promotionCode + " removed successfully.";
        } else {
            return "Promotion with code " + promotionCode + "does not exist.";
        }
    }
}
