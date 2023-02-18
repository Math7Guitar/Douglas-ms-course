package com.mscourse.creditevaluator.module.model.interfaces;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mscourse.creditevaluator.module.model.classes.Card;

@Component
@FeignClient(name = "ms-cards", path = "/cards")
public interface CardsFeignClient {

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    ResponseEntity<Card> getCard(@PathVariable Integer id);

    @GetMapping(value = "/client/{cpf}")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<List<Card>> getCardsByClient(@PathVariable String cpf);
    
}
