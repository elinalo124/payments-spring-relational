package com.payments.relational.mapper;

import com.payments.relational.dto.BankDTO;
import com.payments.relational.entity.Bank;
import org.springframework.stereotype.Component;

@Component
public class BankMapper {

    public static Bank toEntity(BankDTO bankDTO) {
        Bank bank = new Bank();
        bank.setName(bankDTO.getName());
        bank.setCuit(bankDTO.getCuit());
        bank.setAddress(bankDTO.getAddress());
        bank.setTelephone(bankDTO.getTelephone());
        return bank;
    }

    public BankDTO toDTO(Bank bank) {
        BankDTO bankDTO = new BankDTO();
        bankDTO.setName(bank.getName());
        bankDTO.setCuit(bank.getCuit());
        bankDTO.setAddress(bank.getAddress());
        bankDTO.setTelephone(bank.getTelephone());
        return bankDTO;
    }
}
