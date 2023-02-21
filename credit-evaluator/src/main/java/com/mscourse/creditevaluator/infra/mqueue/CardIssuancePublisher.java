package com.mscourse.creditevaluator.infra.mqueue;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import com.mscourse.creditevaluator.module.model.classes.CardIssuanceData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CardIssuancePublisher {
    
    private RabbitTemplate rabbitTemplate;
    private Queue cardsIssuanceQueue;

    public void cardRequest(CardIssuanceData data) throws JsonProcessingException {

        String json = this.convertToJSON(data);
        this.rabbitTemplate.convertAndSend(this.cardsIssuanceQueue.getName(), json);

    }

    private String convertToJSON(CardIssuanceData data) throws JsonProcessingException {
        
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(data);
        return json;

    }
}
