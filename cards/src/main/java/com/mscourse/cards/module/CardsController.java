package com.mscourse.cards.module;


import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mscourse.cards.module.model.entities.Card;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/cards")
public class CardsController {

    private Logger logger = LoggerFactory.getLogger(CardsController.class.getName());

    @Autowired
    private CardsService service;
    
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Card> save(@RequestBody @Valid Card client) {

        logger.info("Save Client API Accessed!");

        try {

            this.service.save(client);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(client.getId()).toUri();
            return ResponseEntity.created(uri).body(client);

        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<List<Card>> getCards() {

        logger.info("Get Client List API Accessed!");

        try {

            return ResponseEntity.ok().body(this.service.getCards());

        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<Card> getCard(@PathVariable Integer id) {

        logger.info("Consult Client API Accessed!");

        try {

            return ResponseEntity.ok().body(this.service.getCard(id));

        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<Card> getCardByRent(@PathVariable Float rent) {

        logger.info("Consult Card by Rent API Accessed!");

        try {

            BigDecimal value = new BigDecimal(Float.toString(rent));
            return ResponseEntity.ok().body(this.service.getCardByRent(value));

        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Card> update(@PathVariable Integer id, @RequestBody @Valid Card updatedCard) {

        logger.info("Update Card API Accessed!");
        
        try {
            this.service.update(id, updatedCard);
            return ResponseEntity.ok().body(updatedCard);
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {

        logger.info("Delete Card API Accessed!");

        try{
            this.service.delete(id);
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    
}
