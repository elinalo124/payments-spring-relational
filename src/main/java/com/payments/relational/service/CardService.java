package com.payments.relational.service;

import com.payments.relational.dto.CardDTO;
import com.payments.relational.entity.Card;

import java.util.List;
import java.util.Optional;

public interface CardService {
    List<Card> getAllCards();
    Optional<Card> getCardById(Long id);
    Card createCard(CardDTO cardDTO);
    List<Card> getCardsCloseToExpiry();
}
