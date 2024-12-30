package com.payments.relational.repository;

import com.payments.relational.entity.Card;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByExpirationDateBetween(LocalDate fromDate, LocalDate toDate);
    @Query("SELECT c FROM Card c " +
            "LEFT JOIN c.purchases p " +
            "GROUP BY c " +
            "ORDER BY COUNT(p) DESC")
    List<Card> findTop10CardsWithMostPurchases(Pageable pageable);
}
