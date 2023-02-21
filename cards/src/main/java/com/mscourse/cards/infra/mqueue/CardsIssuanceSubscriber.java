package com.mscourse.cards.infra.mqueue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mscourse.cards.module.model.classes.CardIssuanceData;
import com.mscourse.cards.module.model.entities.Card;
import com.mscourse.cards.module.model.entities.ClientCard;
import com.mscourse.cards.module.repositories.ClientCardRepository;
import com.mscourse.cards.module.repositories.CardsRepository;

@Component
public class CardsIssuanceSubscriber {

    @Autowired
    private CardsRepository cardsRepository;

    @Autowired
    private ClientCardRepository ccRepository;
    
    @RabbitListener(queues = "${mq.queues.cards-issuance}")
    public void CardIssuanceRequest(@Payload String payload) {
        
        try {
        
            var mapper = new ObjectMapper();
            CardIssuanceData data = mapper.readValue(payload, CardIssuanceData.class);
            Card card = this.cardsRepository.findById(data.getId()).orElseThrow();
            ClientCard clientCard = new ClientCard(data.getCpf(), card, data.getLimit());
            clientCard.setCard(card);

            this.ccRepository.save(clientCard);
            
        } catch(JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
