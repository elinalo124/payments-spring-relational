package com.payments.relational.service;

import com.payments.relational.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getCustomers();
    Customer saveCustomer(Customer customer);
}
