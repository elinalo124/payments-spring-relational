package com.payments.relational.service;

import com.payments.relational.entity.Card;
import com.payments.relational.exception.PaymentsException;

public interface CardService {
    Card saveCard(Card card) throws PaymentsException;
}
