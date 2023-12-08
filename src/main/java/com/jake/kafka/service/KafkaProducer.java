package com.jake.kafka.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    @Value("${app.constant.kafka.topic-name1}")
    private String metricsTopic1;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        kafkaTemplate.send(metricsTopic1, message)
                .whenComplete((result, ex) -> {
                    if(ex == null) {
                        System.out.println("Send message=[" + message + "]");
                    } else {
                        System.out.println("Unable to send message : " + ex.getMessage());
                    }
                });

    }

}
