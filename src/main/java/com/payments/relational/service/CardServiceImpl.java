package com.payments.relational.service;

import com.payments.relational.dto.CardDTO;
import com.payments.relational.entity.Bank;
import com.payments.relational.entity.Card;
import com.payments.relational.entity.Customer;
import com.payments.relational.exception.PaymentsException;
import com.payments.relational.repository.BankRepository;
import com.payments.relational.repository.CardRepository;
import com.payments.relational.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
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
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @Override
    public Optional<Card> getCardById(Long id) {
        return cardRepository.findById(id);
    }

    @Override
    public Card createCard(CardDTO cardDTO) {
        Bank bank =  bankRepository
                .findById(cardDTO.getBankId())
                .orElseThrow(() -> new PaymentsException("Bank with ID " + cardDTO.getBankId() + " not found"));
        Customer customer = customerRepository
                .findById(cardDTO.getCustomerId())
                .orElseThrow(() -> new PaymentsException("Customer with ID " + cardDTO.getCustomerId() + " not found"));

        Card card = new Card();
        card.setCardNumber(cardDTO.getCardNumber());
        card.setCvv(cardDTO.getCvv());
        card.setCardHolderNameInCard(cardDTO.getCardHolderNameInCard());
        card.setSinceDate(cardDTO.getSinceDate());
        card.setExpirationDate(cardDTO.getExpirationDate());
        card.setBank(bank);
        card.setCustomer(customer);
        return cardRepository.save(card);
    }
}
