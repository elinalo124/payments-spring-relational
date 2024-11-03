package com.payments.relational.service;

import com.payments.relational.entity.Bank;
import com.payments.relational.entity.Customer;
import com.payments.relational.exception.PaymentsException;
import com.payments.relational.repository.BankRepository;
import com.payments.relational.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public BankServiceImpl(BankRepository bankRepository, CustomerRepository customerRepository) {
        this.bankRepository = bankRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Bank> getBanks() {
        return bankRepository.findAll();
    }

    @Override
    public Bank saveBank(Bank bank) {
        return bankRepository.save(bank);
    }

    @Override
    public Customer addCustomerToBank(Long customerId, Long bankId) throws PaymentsException {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        Optional<Bank> bankOptional = bankRepository.findById(bankId);
        if (customerOptional.isPresent() && bankOptional.isPresent()) {
            Customer customer = customerOptional.get();
            Bank bank = bankOptional.get();


            if (customer.getBanks().contains(bank)) {
                throw new PaymentsException("The customer is already associated with this bank");
            } else {
                customer.addBank(bank);
                bank.addCustomer(customer);

                customerRepository.save(customer);
                bankRepository.save(bank);
            }

            return customer;
        }else {
            throw new PaymentsException("There was an error adding the client to the bank");
        }
    }

    @Override
    public List<Customer> getCostumersByBankId(Long bankId) throws PaymentsException {
        Optional<Bank> bankOptional = bankRepository.findById(bankId);
        if(bankOptional.isPresent()) {
            Bank bank = bankOptional.get();
            return bank.getCustomers().stream().toList();
        } else {
            throw new PaymentsException("The bank doesn't exist in the system");
        }
    }
}
