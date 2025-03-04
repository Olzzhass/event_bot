package com.example.eventservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void responseAllEvents(String chatId, String response) {
        kafkaTemplate.send("event.all.response", chatId + ";" + response);
    }

}
