package com.payments.relational.repository;

import com.payments.relational.entity.PurchaseSinglePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Set;

public interface PurchaseSinglePaymentRepository extends JpaRepository<PurchaseSinglePayment, Long> {
    @Query("SELECT p FROM Purchase p WHERE p.card.id = :cardId AND FUNCTION('MONTH', p.purchaseDate) = :month AND FUNCTION('YEAR', p.purchaseDate) = :year")
    Set<PurchaseSinglePayment> findByCardAndMonthAndYear(@Param("cardId") Long cardId, @Param("month") int month, @Param("year") int year);
}
