package com.payments.relational.repository;

import com.payments.relational.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByExpirationDateBetween(LocalDate fromDate, LocalDate toDate);
}
