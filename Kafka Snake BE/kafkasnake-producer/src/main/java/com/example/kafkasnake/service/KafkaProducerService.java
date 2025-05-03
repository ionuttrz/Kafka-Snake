package com.example.kafkasnake.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topicName;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate,
                                @Value("${snake.kafka.topic.name}") String topicName) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
    }

    public void sendMessage(String message) {
        kafkaTemplate.send(topicName, message);
    }
}
