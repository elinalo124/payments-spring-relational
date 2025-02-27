package com.payments.relational.controller;

import com.payments.relational.entity.Bank;
import com.payments.relational.entity.Customer;
import com.payments.relational.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(){
        return ResponseEntity.ok().body(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{customerId}/banks")
    public ResponseEntity<List<Bank>> getClientsBanksById(@PathVariable Long customerId) {
        try {
            List<Bank> bankCustomers = customerService.getBanksByCustomerId(customerId);
            return ResponseEntity.ok().body(bankCustomers);
        } catch (Exception e) {
            logger.error("Error getting bank's clients", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        try {
            customerService.createCustomer(customer);
            return ResponseEntity.ok().body(customer);
        } catch (Exception e) {
            logger.error("There was a error saving the customer information", e);
            return ResponseEntity.badRequest().build();
        }
    }
}
