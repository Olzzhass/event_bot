package com.example.gateway.commands;

import com.example.gateway.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class AllEventsCommand implements Command {

    private final KafkaProducerService kafkaProducerService;

    @Override
    public SendMessage execute(Update update, String arguments) {
        String chatId = update.getMessage().getChatId().toString();
        kafkaProducerService.requestAllEvents(chatId);
        return new SendMessage(chatId, "Запрос отправлен, пожалуйста, подождите...");
    }
}
