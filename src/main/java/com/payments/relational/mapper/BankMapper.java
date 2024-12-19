package com.payments.relational.mapper;

import com.payments.relational.dto.BankDTO;
import com.payments.relational.entity.Bank;
import com.payments.relational.entity.Card;
import com.payments.relational.entity.Customer;
import com.payments.relational.entity.Promotion;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;

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

        bankDTO.setCustomerIds(Optional.ofNullable(bank.getCustomers())
                .orElse(new HashSet<>())
                .stream()
                .map(Customer::getId)
                .collect(HashSet::new, HashSet::add, HashSet::addAll));

        bankDTO.setCardIds(Optional.ofNullable(bank.getCards())
                .orElse(new HashSet<>())
                .stream()
                .map(Card::getId)
                .collect(HashSet::new, HashSet::add, HashSet::addAll));

        bankDTO.setPromotionIds(Optional.ofNullable(bank.getPromotions())
                .orElse(new HashSet<>())
                .stream()
                .map(Promotion::getId)
                .collect(HashSet::new, HashSet::add, HashSet::addAll));

        return bankDTO;
    }
}
