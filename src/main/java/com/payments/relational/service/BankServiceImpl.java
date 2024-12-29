package com.payments.relational.service;

import com.payments.relational.dto.BankDTO;
import com.payments.relational.entity.Bank;
import com.payments.relational.entity.Customer;
import com.payments.relational.exception.PaymentsException;
import com.payments.relational.mapper.BankMapper;
import com.payments.relational.repository.BankRepository;
import com.payments.relational.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;
    private final CustomerRepository customerRepository;
    private final BankMapper mapper;
    Logger logger = LoggerFactory.getLogger(BankServiceImpl.class);

    @Autowired
    public BankServiceImpl(
            BankRepository bankRepository,
            CustomerRepository customerRepository,
            BankMapper bankMapper
    ) {
        this.bankRepository = bankRepository;
        this.customerRepository = customerRepository;
        this.mapper = bankMapper;
    }

    @Override
    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    @Override
    public Bank getBankById(Long id) throws PaymentsException {
        Optional<Bank> bankOptional = bankRepository.findById(id);
        if(bankOptional.isPresent()) {
            return bankOptional.get();
        } else {
            throw new PaymentsException("There was an error fetching the bank's information");
        }
    }

    @Override
    public BankDTO saveBank(BankDTO bankDTO) {
        Bank bank = BankMapper.toEntity(bankDTO);
        Set<Customer> customers = new HashSet<>();
        if (bankDTO.getMembersId() != null && !bankDTO.getMembersId().isEmpty()) {
            for (Long customerId : bankDTO.getMembersId()) {
                Customer customer = customerRepository.findById(customerId)
                        .orElseThrow(() -> new PaymentsException("Customer not found with ID: " + customerId));
                customers.add(customer);
            }
        }
        bank.getMembers().addAll(customers);
        bankRepository.save(bank);
        return bankDTO;
    }

    @Override
    public Customer addCustomerToBank(Long customerId, Long bankId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        Optional<Bank> bankOptional = bankRepository.findById(bankId);
        if (customerOptional.isPresent() && bankOptional.isPresent()) {
            Customer customer = customerOptional.get();
            Bank bank = bankOptional.get();
            if (customer.getBanks().contains(bank)) {
                throw new PaymentsException("The customer is already associated with this bank");
            } else {
                bank.addCustomer(customer);
            }
            return customer;
        } else {
            throw new PaymentsException("There was an error adding the client to the bank");
        }
    }

    @Override
    public List<Customer> getCostumersByBankId(Long bankId) {
        Optional<Bank> bankOptional = bankRepository.findById(bankId);
        if(bankOptional.isPresent()) {
            return bankRepository.findMembersByBankId(bankId).stream().toList();
        } else {
            throw new PaymentsException("The bank doesn't exist in the system");
        }
    }
}
