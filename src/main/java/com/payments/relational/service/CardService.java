package com.payments.relational.service;

import com.payments.relational.entity.Card;
import com.payments.relational.exception.PaymentsException;

import java.util.List;
import java.util.Optional;

public interface CardService {
    List<Card> getAllCards();
    Optional<Card> getCardById(Long id);
    Card createCard(Card card) throws PaymentsException;
}
