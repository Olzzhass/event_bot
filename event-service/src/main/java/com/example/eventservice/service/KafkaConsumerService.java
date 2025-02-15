package com.example.eventservice.service;

import com.example.eventservice.mapper.EventMapper;
import com.example.eventservice.model.Event;
import com.example.shared.dto.EventDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {

    private final EventService eventService;

    @KafkaListener(topics = "event.create.request", groupId = "event-create-group")
    public void consumeEvent(EventDto eventDto) {
        log.info("📥 Получен EventDto из Kafka: {}", eventDto);

        Event savedEvent = eventService.save(eventDto);

        log.info("✅ Сохранен Event в базу данных: {}", savedEvent);
    }
}
