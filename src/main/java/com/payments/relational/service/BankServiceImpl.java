package com.payments.relational.service;

import com.payments.relational.dto.BankDTO;
import com.payments.relational.entity.Bank;
import com.payments.relational.entity.Card;
import com.payments.relational.entity.Customer;
import com.payments.relational.exception.PaymentsException;
import com.payments.relational.mapper.BankMapper;
import com.payments.relational.repository.BankRepository;
import com.payments.relational.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;
    private final CustomerRepository customerRepository;
    private final BankMapper mapper;

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
        Set<Card> cards = new HashSet<>();
        if (bankDTO.getCustomerIds() != null && !bankDTO.getCustomerIds().isEmpty()) {
            for (Long customerId : bankDTO.getCustomerIds()) {
                Customer customer = customerRepository.findById(customerId)
                        .orElseThrow(() -> new PaymentsException("Customer not found with ID: " + customerId));
                customers.add(customer);
            }
        }
        bank.setCustomers(customers);
        bank.setCards(cards);
        bankRepository.save(bank);

        return bankDTO;
    }

    @Override
    public Bank createBank(Bank bank) {
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
