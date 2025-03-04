package com.example.eventservice.service;

import com.example.eventservice.mapper.EventMapper;
import com.example.eventservice.model.Event;
import com.example.eventservice.model.Subscription;
import com.example.shared.dto.EventDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {

    private final EventService eventService;
    private final KafkaProducerService kafkaProducerService;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final SubscriptionService subscriptionService;

    @KafkaListener(topics = "event.create.request", groupId = "event-create-group")
    public void consumeEvent(EventDto eventDto) {
        log.info("📥 Получен EventDto из Kafka: {}", eventDto);

        Event savedEvent = eventService.save(eventDto);

        log.info("✅ Сохранен Event в базу данных: {}", savedEvent);

//        kafkaProducerService.responseCreateEvent();
    }

    @KafkaListener(topics = "event.all.request", groupId = "event-group")
    public void processAllEventsRequest(String chatId) {
        List<Event> events = eventService.findAll();

        String response = events.isEmpty() ? "Нет доступных событий." :
                events.stream()
                        .map(event -> event.getId() + " - " + event.getTitle() + " - " + event.getDate())
                        .collect(Collectors.joining("\n"));

        kafkaProducerService.responseAllEvents(chatId, response);
    }

    @KafkaListener(topics = "event.subscribe.request", groupId = "event-service")
    public void handleSubscription(String message) {
        String[] parts = message.split(":");
        if(parts.length < 2) return;

        String chatId= parts[0];
        Long eventId = Long.parseLong(parts[1]);

        if (subscriptionService.existsByChatIdAndEventId(chatId, eventId)) {
            return;
        }

        Event event = eventService.findById(eventId).orElse(null);
        if (event == null) return;

        Subscription subscription = new Subscription();
        subscription.setChatId(chatId);
        subscription.setEvent(event);

        subscriptionService.save(subscription);
    }
}
