package com.payments.relational.controller;

import com.payments.relational.entity.Bank;
import com.payments.relational.entity.Customer;
import com.payments.relational.entity.Purchase;
import com.payments.relational.service.BankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banking")
public class BankController {

    Logger logger = LoggerFactory.getLogger(BankController.class);

    private final BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bank> getBankById(@PathVariable Long id) {
        return bankService.getBankById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Bank>> getAllBanks() {
        return ResponseEntity.ok().body(bankService.getAllBanks());
    }

    @PostMapping
    public ResponseEntity<Bank> createBank(@RequestBody Bank bank) {
        try {
            bankService.createBank(bank);
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

    @GetMapping("{bankId}/customers")
    public ResponseEntity<List<Customer>> getBankClients(@PathVariable Long bankId) {
        try {
            List<Customer> bankCustomers = bankService.getCostumersByBankId(bankId);
            return ResponseEntity.ok().body(bankCustomers);
        } catch (Exception e) {
            logger.error("Error getting bank's clients", e);
            return ResponseEntity.badRequest().build();
        }
    }
}
