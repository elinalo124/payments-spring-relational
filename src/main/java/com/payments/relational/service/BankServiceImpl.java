package com.payments.relational.service;

import com.payments.relational.entity.Bank;
import com.payments.relational.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    BankRepository bankRepository;

    @Override
    public List<Bank> getBanks() {
        return bankRepository.findAll();
    }

    @Override
    public Bank saveBank(Bank bank) {
        return bankRepository.save(bank);
    }
}
