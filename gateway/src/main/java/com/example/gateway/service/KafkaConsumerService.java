package com.example.gateway.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final TelegramBot telegramBot;

    @KafkaListener(topics = "event.all.response", groupId = "event-group")
    public void receiveAllEvents(String message) {
        String[] parts = message.split(";", 2);
        String chatId = parts[0];
        String events = parts.length > 1 ? parts[1] : "Нет событий.";

        SendMessage sendMessage = new SendMessage(chatId, events);
        try {
            telegramBot.execute(sendMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
