package com.payments.relational.service;

import com.payments.relational.entity.Bank;
import com.payments.relational.entity.Customer;
import com.payments.relational.repository.BankRepository;
import com.payments.relational.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    BankRepository bankRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Bank> getBanks() {
        return bankRepository.findAll();
    }

    @Override
    public Bank saveBank(Bank bank) {
        return bankRepository.save(bank);
    }

    @Override
    public void addCustomerToBank(Long customerId, Long bankId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        Optional<Bank> bankOptional = bankRepository.findById(bankId);
        if (customerOptional.isPresent() && bankOptional.isPresent()) {
            Customer customer = customerOptional.get();
            Bank bank = bankOptional.get();
            customer.addBank(bank);
            bank.addCustomer(customer);
        }
    }
}
