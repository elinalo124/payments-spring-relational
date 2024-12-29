package com.payments.relational.repository;

import com.payments.relational.entity.PurchaseSinglePayment;
import com.payments.relational.entity.Quota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuotaRepository extends JpaRepository<Quota, Long> {
}
