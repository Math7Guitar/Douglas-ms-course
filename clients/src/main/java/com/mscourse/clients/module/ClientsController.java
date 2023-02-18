package com.mscourse.clients.module;

import java.net.URI;
import java.util.List;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import com.mscourse.clients.module.model.entities.Client;

import jakarta.validation.Valid;

@RefreshScope
@RestController
@RequestMapping(value = "/clients")
public class ClientsController {

    private Logger logger = LoggerFactory.getLogger(ClientsController.class.getName());

    @Autowired
    private ClientsService service;
    
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Client> save(@RequestBody @Valid Client client) {

        logger.info("Save Client API Accessed!");

        try {

            client.setCpf(client.getCpf().replace(".", "").replace("-", ""));

            this.service.save(client);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(client.getId()).toUri();
            return ResponseEntity.created(uri).body(client);

        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<List<Client>> getClients() {

        logger.info("Get Client List API Accessed!");

        try {

            return ResponseEntity.ok().body(this.service.getClients());

        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping(value = "/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<Client> getClient(@PathVariable String cpf) {

        logger.info("Consult Client API Accessed!");

        try {

            return ResponseEntity.ok().body(this.service.getClient(cpf.replace(".", "").replace("-", "")));

        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping(value = "/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> update(@PathVariable String cpf, @RequestBody @Valid Client updatedClient) {

        logger.info("Update Client API Accessed!");
        
        try {
            this.service.update(cpf, updatedClient);
            return ResponseEntity.ok().body(updatedClient);
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping(value = "/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable String cpf) {

        logger.info("Delete Client API Accessed!");

        try{
            this.service.delete(cpf);
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    
}
