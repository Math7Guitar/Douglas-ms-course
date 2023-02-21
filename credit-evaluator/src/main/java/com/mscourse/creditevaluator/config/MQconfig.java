package com.mscourse.creditevaluator.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.beans.factory.annotation.Value;

@Configuration
public class MQconfig {
    
    //@Value("${mq.queue.cards-issuance}")
    private String queue = "cards-issuance";


    @Bean
    public Queue queueCardsIssuance() {
        return new Queue(queue, true);
    }
}
