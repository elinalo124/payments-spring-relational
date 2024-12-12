package com.payments.relational.service;

import com.payments.relational.entity.Bank;
import com.payments.relational.entity.Customer;
import com.payments.relational.exception.PaymentsException;
import java.util.List;

public interface BankService {
    List<Bank> getAllBanks();
    Bank getBankById(Long id) throws PaymentsException;
    Bank createBank(Bank bank);
    Customer addCustomerToBank(Long customerId, Long bankId) throws PaymentsException;
    List<Customer> getCostumersByBankId(Long bankId) throws PaymentsException;
}
