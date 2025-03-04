package com.example.gateway.service;

import com.example.shared.dto.EventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, EventDto> kafkaTemplate;
    private final KafkaTemplate<String, String> stringKafkaTemplate;

    public void sendEvent(EventDto event) {
        kafkaTemplate.send("event.create.request", event);
    }

    public void requestAllEvents(String chatId) {
        stringKafkaTemplate.send("event.all.request", chatId);
    }

    public void sendSubscription(String chatId, String eventId) {
        String message = chatId + ":" + eventId;
        stringKafkaTemplate.send("event.subsribe.request", message);
    }
}
