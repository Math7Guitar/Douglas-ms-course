package com.mscourse.creditevaluator.module;

import java.util.List;

import java.net.URI;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.mscourse.creditevaluator.exception.ClientStatusNotFoundException;
import com.mscourse.creditevaluator.exception.MicroServicesComunicationException;
import com.mscourse.creditevaluator.module.model.classes.EvaluationResponse;
import com.mscourse.creditevaluator.module.model.classes.EvaluationRequest;
import com.mscourse.creditevaluator.module.model.classes.CardIssuanceData;
import com.mscourse.creditevaluator.module.model.classes.ClientStatus;
import com.mscourse.creditevaluator.module.model.classes.CardRequestProtocol;

import com.mscourse.creditevaluator.exception.CardRequestException;

@RestController
@RefreshScope
@RequestMapping(value = "/ce")
public class CEController {

    private Logger logger = LoggerFactory.getLogger(CEController.class.getName());
    
    @Autowired
    private CEService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<ClientStatus> getClientStatus(@RequestParam Integer cardId) {
        
        logger.info("Get Client Status API Accessed!");

        try {

            return ResponseEntity.ok().body(this.service.getClientStatus(cardId));

        } catch(MicroServicesComunicationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch(ClientStatusNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EvaluationResponse>> getEvaluation(@RequestBody EvaluationRequest data) {

        try{

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(data.getCpf()).toUri();
            return ResponseEntity.created(uri).body(this.service.getEvaluationResponse(data));

        } catch(MicroServicesComunicationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch(ClientStatusNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/card-request")
    public ResponseEntity cardRequest(@RequestBody CardIssuanceData data) {

        try {

            CardRequestProtocol protocol = this.service.cardIssuanceRequest(data);
            return ResponseEntity.ok(protocol);

        } catch(CardRequestException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
