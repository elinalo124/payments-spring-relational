package com.payments.relational.service;

import com.payments.relational.entity.Bank;
import com.payments.relational.entity.Card;
import com.payments.relational.entity.Customer;
import com.payments.relational.exception.PaymentsException;
import com.payments.relational.repository.BankRepository;
import com.payments.relational.repository.CardRepository;
import com.payments.relational.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardServiceImpl implements CardService{

    private final CardRepository cardRepository;
    private final BankRepository bankRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository, BankRepository bankRepository, CustomerRepository customerRepository) {
        this.cardRepository = cardRepository;
        this.bankRepository = bankRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Card saveCard(Card card) throws PaymentsException {
        Optional<Customer> customerOptional = customerRepository.findById(card.getCustomer().getId());
        Optional<Bank> bankOptional = bankRepository.findById(card.getBank().getId());
        if (customerOptional.isPresent() && bankOptional.isPresent()) {
            cardRepository.save(card);
        } else {
            throw new PaymentsException("The bank or the client doesn't exist in the system");
        }
        return null;
    }
}
