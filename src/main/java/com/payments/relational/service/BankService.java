package com.payments.relational.service;

import com.payments.relational.entity.Bank;

import java.util.List;

public interface BankService {
    List<Bank> getBanks();
    Bank saveBank(Bank bank);
}
