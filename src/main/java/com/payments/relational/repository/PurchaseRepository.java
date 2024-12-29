package com.payments.relational.repository;

import com.payments.relational.entity.Promotion;
import com.payments.relational.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findByValidPromotion(Promotion promotion);
}
