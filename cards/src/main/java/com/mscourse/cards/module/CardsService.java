package com.mscourse.cards.module;

//import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.mscourse.cards.module.model.entities.Card;

@Service
public class CardsService {
    @Autowired
    private CardsRepository repository;

    public Card save(Card card) {
        return this.repository.save(card);
    }

    public Card getCard(Integer id) {
        return this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found!"));
    }

    /*public List<Card> getCardByRent(BigDecimal rent) {
        return this.repository.findByRentLessThanEqual(rent).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Card list not found!"));
    }*/

    public List<Card> getCardByClient(String client) {
        return this.repository.findByClient(client).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Card list not found!"));
    }

    public List<Card> getCards() {
        return this.repository.findAll();
    }

    public void attachCard() {

    }

    public Card update(Integer id, Card updatedClient) {
        return this.repository.findById(id).map( client -> {
            updatedClient.setId(client.getId());
            return this.repository.save(updatedClient);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void delete(Integer id) {
        this.repository.findById(id).map( client -> {
            this.repository.delete(client);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
