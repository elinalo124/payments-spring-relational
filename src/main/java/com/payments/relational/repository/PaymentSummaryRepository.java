package com.payments.relational.repository;

import com.payments.relational.entity.PaymentSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentSummaryRepository extends JpaRepository<PaymentSummary, Long> {
}
