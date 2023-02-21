package com.mscourse.creditevaluator.module;

import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import com.mscourse.creditevaluator.exception.ClientStatusNotFoundException;
import com.mscourse.creditevaluator.exception.MicroServicesComunicationException;
import com.mscourse.creditevaluator.exception.CardRequestException;
import com.mscourse.creditevaluator.module.model.classes.Card;
import com.mscourse.creditevaluator.module.model.classes.Client;
import com.mscourse.creditevaluator.module.model.classes.ClientStatus;
import com.mscourse.creditevaluator.module.model.interfaces.CardsFeignClient;
import com.mscourse.creditevaluator.module.model.interfaces.ClientsFeignClient;
import com.mscourse.creditevaluator.module.model.classes.EvaluationResponse;
import com.mscourse.creditevaluator.module.model.classes.EvaluationRequest;
import com.mscourse.creditevaluator.module.model.classes.CardIssuanceData;
import com.mscourse.creditevaluator.module.model.classes.CardRequestProtocol;
import com.mscourse.creditevaluator.infra.mqueue.CardIssuancePublisher;

import feign.FeignException.FeignClientException;


@Service
public class CEService {

    @Autowired
    private CardsFeignClient cfc;

    @Autowired
    private ClientsFeignClient clifc;

    private CardIssuancePublisher publisher;


    public ClientStatus getClientStatus(Integer id) throws ClientStatusNotFoundException, MicroServicesComunicationException {
        
        try {

            Card card = cfc.getCard(id).getBody();
            Client client = clifc.getClient(card.getClient()).getBody();
            List<Card> clientCards = cfc.getCardsByClient(client.getCpf()).getBody();
            return new ClientStatus(client.getId(), client.getName(), clientCards);

        } catch(FeignClientException e) {
            int status = e.status();
            if(HttpStatus.NOT_FOUND.value() == status) {
                throw new ClientStatusNotFoundException();
            }

            throw new MicroServicesComunicationException(status, e.getMessage());
        }

    }


    public List<EvaluationResponse> getEvaluationResponse(EvaluationRequest data) throws ClientStatusNotFoundException, MicroServicesComunicationException {
        
        try {

            List<Card> cards = cfc.getCardsByRent(data.rent, data.cpf).getBody();

            List<EvaluationResponse> res = new ArrayList<EvaluationResponse>();
            
            cards.forEach(card -> res.add(new EvaluationResponse(card.getName(), card.getFlag().toString(), card.getLimit().floatValue() * 2)));

            return res;
        } catch(FeignClientException e) {
            int status = e.status();
            if(HttpStatus.NOT_FOUND.value() == status) {
                throw new ClientStatusNotFoundException();
            }

            throw new MicroServicesComunicationException(status, e.getMessage());
        }
    }


    public CardRequestProtocol cardIssuanceRequest(CardIssuanceData data) {

        try {

            publisher.cardRequest(data);
            var protocol = UUID.randomUUID().toString();
            return new CardRequestProtocol(protocol);

        } catch(Exception e) {
            throw new CardRequestException(e.getMessage());
        }
    }
}
