package com.payments.relational.service;

import com.payments.relational.dto.FinancingDTO;
import com.payments.relational.entity.Bank;
import com.payments.relational.entity.Financing;
import com.payments.relational.entity.Promotion;
import com.payments.relational.entity.Purchase;
import com.payments.relational.exception.PaymentsException;
import com.payments.relational.repository.BankRepository;
import com.payments.relational.repository.FinancingRepository;
import com.payments.relational.repository.PromotionRepository;
import com.payments.relational.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PromotionServiceImpl implements PromotionService {
    private final PromotionRepository promotionRepository;
    private final BankRepository bankRepository;
    private final PurchaseRepository purchaseRepository;
    private final FinancingRepository financingRepository;

    @Autowired
    public PromotionServiceImpl(
            PromotionRepository promotionRepository,
            BankRepository bankRepository,
            PurchaseRepository purchaseRepository,
            FinancingRepository financingRepository
    ) {
        this.promotionRepository = promotionRepository;
        this.bankRepository = bankRepository;
        this.purchaseRepository = purchaseRepository;
        this.financingRepository = financingRepository;
    }

    @Override
    public List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }

    // 1) Agregar una promocion de tipo Financing a un banco dado
    @Transactional
    @Override
    public Promotion savePromotion(Long bankId, FinancingDTO promo) {
        Optional<Bank> bankOptional= bankRepository.findById(bankId);
         if (bankOptional.isPresent()) {
             Bank bank = bankOptional.get();
             Financing newPromo = new Financing();
             newPromo.setCode(promo.getCode());
             newPromo.setPromotionTitle(promo.getPromotionTitle());
             newPromo.setNameStore(promo.getNameStore());
             newPromo.setCuitStore(promo.getCuitStore());
             newPromo.setValidityStartDate(promo.getValidityStartDate());
             newPromo.setValidityEndDate(promo.getValidityEndDate());
             newPromo.setComments(promo.getComments());
             newPromo.setNumberOfQuotas(promo.getNumberOfQuotas());
             newPromo.setInterest(promo.getInterest());
             Financing savedPromo = promotionRepository.save(newPromo);
             bank.addPromo(savedPromo);
             bankRepository.save(bank);
             return savedPromo;
        } else {
             throw new PaymentsException("There is no bank with the Id given");
         }
    }

    // 2) Extender el tiempo de validez de una promocion
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

    // 6) Eliminar una promocion a traves de su codigo
    // tener en cuenta que esta puede haber sido aplicada a alguna compra
    @Transactional
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

    @Override
    public List<Promotion> getValidPromotionsInRange(String cuitStore, LocalDate validityStartDate, LocalDate validityEndDate) {
        //return promotionRepository.findByCuitStoreAndValidityStartDateLessThanEqualAndValidityEndDateGreaterThanEqual(cuitStore, validityStartDate, validityEndDate);
        return promotionRepository.findValidPromotionsInRange(cuitStore, validityStartDate, validityEndDate);
    }

    @Override
    public Promotion getMostUsedPromotion() {
        return promotionRepository.findMostUsedPromotion().get(0);
    }

}
