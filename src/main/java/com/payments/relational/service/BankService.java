package com.payments.relational.service;

import com.payments.relational.dto.BankCustomerDTO;
import com.payments.relational.dto.BankDTO;
import com.payments.relational.entity.Bank;
import com.payments.relational.entity.Customer;
import com.payments.relational.exception.PaymentsException;
import java.util.List;

public interface BankService {
    List<Bank> getAllBanks();
    Bank getBankById(Long id) throws PaymentsException;
    BankDTO saveBank(BankDTO bankDTO);
    Customer addCustomerToBank(Long customerId, Long bankId);
    List<Customer> getCostumersByBankId(Long bankId);
    List<BankCustomerDTO> getCustomersAmountPerBank();
}
