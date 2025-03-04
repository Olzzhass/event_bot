package com.example.gateway.commands;

import com.example.gateway.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class SubscribeCommand implements Command {

    private final KafkaProducerService kafkaProducerService;

    @Override
    public SendMessage execute(Update update) {
        Long chatId = update.getMessage().getChatId();
        String messageText = update.getMessage().getText();

        String[] parts = messageText.split("\\s+");
        if (parts.length < 2) {
            return new SendMessage(chatId.toString(), "❌ Использование: /subscribe <event_id>");
        }

        String eventId = parts[1];
        kafkaProducerService.sendSubscription(chatId.toString(), eventId);

        return new SendMessage(chatId.toString(), "✅ Ваша подписка на событие " + eventId + " отправлено в систему");
    }
}
