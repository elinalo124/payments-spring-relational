package com.payments.relational.service;

import com.payments.relational.entity.Bank;
import com.payments.relational.entity.Promotion;
import com.payments.relational.exception.PaymentsException;
import com.payments.relational.repository.BankRepository;
import com.payments.relational.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PromotionServiceImpl implements PromotionService {
    private final PromotionRepository promotionRepository;
    private final BankRepository bankRepository;

    @Autowired
    public PromotionServiceImpl(
            PromotionRepository promotionRepository,
            BankRepository bankRepository
    ) {
        this.promotionRepository = promotionRepository;
        this.bankRepository = bankRepository;
    }

    @Override
    public Promotion savePromotion(Long bankId, Promotion promo) {
        Bank bank = bankRepository.findById(bankId)
                .orElseThrow(() -> new PaymentsException("Bank not found"));
        promo.setBank(bank);
        bank.getPromotions().add(promo);
        return promotionRepository.save(promo);
    }

    @Override
    public List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }
}
