package com.example.gateway.service;

import com.example.shared.dto.EventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, EventDto> kafkaTemplate;

    public void sendEvent(EventDto event) {
        kafkaTemplate.send("event.create.request", event);
    }

}
