package com.payments.relational.controller;

import com.payments.relational.dto.CardDTO;
import com.payments.relational.entity.Card;
import com.payments.relational.service.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardsController {

    Logger logger = LoggerFactory.getLogger(CardsController.class);

    private final CardService cardService;

    @Autowired
    public CardsController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> getCardById(@PathVariable Long id) {
        return cardService.getCardById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Card>> getAllCards() {
        return ResponseEntity.ok().body(cardService.getAllCards());
    }

    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody CardDTO cardDTO) {
        try {
            Card card = cardService.createCard(cardDTO);
            return ResponseEntity.ok().body(card);
        } catch (Exception e) {
            logger.error("There was a error saving the Bank information", e);
            return ResponseEntity.badRequest().build();
        }
    }
}
