package com.payments.relational.repository;

import com.payments.relational.entity.Financing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancingRepository extends JpaRepository<Financing, Long> {
}
