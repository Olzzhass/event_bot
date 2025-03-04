package com.example.eventservice.service;

import com.example.eventservice.model.Subscription;
import com.example.eventservice.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public boolean existsByChatIdAndEventId(String chatId, Long eventId) {
        return subscriptionRepository.existsByChatIdAndEventId(chatId, eventId);
    }

    public void save(Subscription subscription) {
        subscriptionRepository.save(subscription);
    }
}
