package com.payments.relational.controller;

import com.payments.relational.entity.Bank;
import com.payments.relational.entity.Customer;
import com.payments.relational.exception.PaymentsException;
import com.payments.relational.service.BankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banks")
public class BankController {

    Logger logger = LoggerFactory.getLogger(BankController.class);

    @Autowired
    BankService bankService;

    @GetMapping()
    public ResponseEntity<List<Bank>> getBanks(){
        return ResponseEntity.ok().body(bankService.getBanks());
    }

    @PostMapping
    public ResponseEntity<Bank> saveBank(@RequestBody Bank bank) {
        try {
            bankService.saveBank(bank);
            return ResponseEntity.ok().body(bank);
        } catch (Exception e) {
            logger.error("There was a error saving the Bank information", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/customers/{customerId}/banks/{bankId}")
    public ResponseEntity<Customer> addCustomerToBank(@PathVariable Long customerId, @PathVariable Long bankId) {
        try {
            Customer customer = bankService.addCustomerToBank(customerId, bankId);
            return ResponseEntity.ok().body(customer);
        } catch (Exception e) {
            logger.error("Error adding client to bank", e);
            return ResponseEntity.badRequest().build();
        }
    }
}
