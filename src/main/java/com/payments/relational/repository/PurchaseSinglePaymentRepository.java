package com.payments.relational.repository;

import com.payments.relational.entity.PurchaseSinglePayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseSinglePaymentRepository extends JpaRepository<PurchaseSinglePayment, Long> {
}
