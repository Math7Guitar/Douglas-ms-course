package com.mscourse.creditevaluator.module.model.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mscourse.creditevaluator.module.model.classes.Client;

@Component
@FeignClient(name = "ms-clients", path = "/clients")
public interface ClientsFeignClient {

    @GetMapping(value = "/{cpf}")
    @ResponseStatus(HttpStatus.FOUND)
    ResponseEntity<Client> getClient(@PathVariable String cpf);
    
}
