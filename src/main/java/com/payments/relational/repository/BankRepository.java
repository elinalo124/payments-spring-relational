package com.payments.relational.repository;

import com.payments.relational.entity.Bank;
import com.payments.relational.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface BankRepository extends JpaRepository<Bank, Long> {
    @Query("SELECT b.members FROM Bank b WHERE b.id = :bankId")
    Set<Customer> findMembersByBankId(@Param("bankId") Long bankId);
}
