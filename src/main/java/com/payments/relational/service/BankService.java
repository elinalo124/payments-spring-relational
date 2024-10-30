package com.payments.relational.service;

import com.payments.relational.entity.Bank;
import com.payments.relational.entity.Customer;

import java.util.List;

public interface BankService {
    List<Bank> getBanks();
    Bank saveBank(Bank bank);
    void addCustomerToBank(Long customerId, Long bankId);
}
