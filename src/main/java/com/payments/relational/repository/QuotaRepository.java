package com.payments.relational.repository;

import com.payments.relational.entity.Quota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuotaRepository extends JpaRepository<Quota, Long> {
    @Query("SELECT q FROM Quota q WHERE q.monthh = :month AND q.yearr = :year AND q.purchase.card.id = :cardId")
    List<Quota> findQuotasByMonthYearAndCardId(
            @Param("month") int month,
            @Param("year") int year,
            @Param("cardId") Long cardId
    );
}
