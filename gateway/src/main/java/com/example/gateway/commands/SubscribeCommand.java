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
    public SendMessage execute(Update update, String arguments) {
        Long chatId = update.getMessage().getChatId();

        if(arguments == null || !arguments.matches("\\d++")) {
            return new SendMessage(chatId.toString(),
                    "Введите корректный номер события, например: /subscribe 1");
        }

        kafkaProducerService.sendSubscription(chatId.toString(), arguments);

        return new SendMessage(chatId.toString(),
                "✅ Ваша подписка на событие " + arguments + " отправлено в систему");
    }
}
