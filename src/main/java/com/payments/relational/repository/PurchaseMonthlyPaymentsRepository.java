package com.payments.relational.repository;

import com.payments.relational.entity.PurchaseMonthlyPayments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseMonthlyPaymentsRepository extends JpaRepository<PurchaseMonthlyPayments, Long> {
}
