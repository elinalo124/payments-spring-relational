package com.payments.relational.repository;

import com.payments.relational.entity.PurchaseMonthlyPayments;
import com.payments.relational.entity.Quota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Set;

public interface PurchaseMonthlyPaymentsRepository extends JpaRepository<PurchaseMonthlyPayments, Long> {
    @Query("SELECT p FROM Purchase p WHERE p.card.id = :cardId AND FUNCTION('MONTH', p.purchase_date) = :month AND FUNCTION('YEAR', p.purchase_date) = :year")
    Set<PurchaseMonthlyPayments> findByCardAndMonthAndYear(@Param("cardId") Long cardId, @Param("month") int month, @Param("year") int year);

    @Query("SELECT p.quotas FROM PurchaseMonthlyPayments p " +
            "WHERE p.card.id = :cardId " +
            "AND FUNCTION('MONTH', p.purchase_date) = :month " +
            "AND FUNCTION('YEAR', p.purchase_date) = :year")
    Set<Quota> findQuotasByCardAndDate(@Param("cardId") Long cardId,
                                       @Param("month") int month,
                                       @Param("year") int year);

}
