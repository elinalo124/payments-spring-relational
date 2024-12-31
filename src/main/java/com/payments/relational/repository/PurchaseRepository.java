package com.payments.relational.repository;

import com.payments.relational.entity.Promotion;
import com.payments.relational.entity.Purchase;
import com.payments.relational.entity.PurchaseMonthlyPayments;
import com.payments.relational.entity.PurchaseSinglePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findByValidPromotion(Promotion promotion);

    @Query("SELECT p FROM PurchaseMonthlyPayments p WHERE p.card.id = :cardId AND " +
            "FUNCTION('MONTH', p.purchaseDate) = :month AND FUNCTION('YEAR', p.purchaseDate) = :year")
    List<PurchaseMonthlyPayments> findMonthlyPaymentsByCardAndDate(@Param("cardId") Long cardId,
                                                                   @Param("month") int month,
                                                                   @Param("year") int year);

    @Query("SELECT p FROM PurchaseSinglePayment p WHERE p.card.id = :cardId AND " +
            "FUNCTION('MONTH', p.purchaseDate) = :month AND FUNCTION('YEAR', p.purchaseDate) = :year")
    List<PurchaseSinglePayment> findSinglePaymentsByCardAndDate(@Param("cardId") Long cardId,
                                                                @Param("month") int month,
                                                                @Param("year") int year);

    @Query("SELECT p.store AS store, p.cuitStore AS cuitStore, SUM(p.finalAmount) AS totalAmount " +
            "FROM Purchase p " +
            "WHERE EXTRACT(MONTH FROM p.purchaseDate) = :month " +
            "AND EXTRACT(YEAR FROM p.purchaseDate) = :year " +
            "GROUP BY p.store, p.cuitStore " +
           "ORDER BY totalAmount DESC LIMIT 1"
    )
    Map<String, Object> findTopStoreByMonthAndYear(@Param("month") int month, @Param("year") int year);

}
