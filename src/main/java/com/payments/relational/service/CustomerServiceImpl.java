package com.payments.relational.service;

import com.payments.relational.entity.Bank;
import com.payments.relational.entity.Customer;
import com.payments.relational.exception.PaymentsException;
import com.payments.relational.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Bank> getBanksByCustomerId(Long customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if(customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            return customer.getBanks().stream().toList();
        } else {
            throw new PaymentsException("The bank doesn't exist in the system");
        }
    }
}
