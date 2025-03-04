package com.example.eventservice.repository;

import com.example.eventservice.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByChatId(String chatId);
    boolean existsByChatIdAndEventId(String chatId, Long eventId);
}
