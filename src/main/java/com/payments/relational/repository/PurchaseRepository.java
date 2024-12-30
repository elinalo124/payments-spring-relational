package com.payments.relational.repository;

import com.payments.relational.entity.Promotion;
import com.payments.relational.entity.Purchase;
import com.payments.relational.entity.PurchaseMonthlyPayments;
import com.payments.relational.entity.PurchaseSinglePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findByValidPromotion(Promotion promotion);

    @Query("SELECT p FROM PurchaseMonthlyPayments p WHERE p.card.id = :cardId AND " +
            "FUNCTION('MONTH', p.purchase_date) = :month AND FUNCTION('YEAR', p.purchase_date) = :year")
    List<PurchaseMonthlyPayments> findMonthlyPaymentsByCardAndDate(@Param("cardId") Long cardId,
                                                                   @Param("month") int month,
                                                                   @Param("year") int year);

    @Query("SELECT p FROM PurchaseSinglePayment p WHERE p.card.id = :cardId AND " +
            "FUNCTION('MONTH', p.purchase_date) = :month AND FUNCTION('YEAR', p.purchase_date) = :year")
    List<PurchaseSinglePayment> findSinglePaymentsByCardAndDate(@Param("cardId") Long cardId,
                                                                @Param("month") int month,
                                                                @Param("year") int year);

}
