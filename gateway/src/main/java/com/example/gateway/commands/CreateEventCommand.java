package com.example.gateway.commands;

import com.example.shared.dto.EventDto;
import com.example.gateway.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CreateEventCommand implements Command {

    private final KafkaProducerService kafkaProducerService;
    private final Map<Long, EventDto> userEventData = new HashMap<>();
    private final Map<Long, Integer> userStep = new HashMap<>();

    @Override
    public SendMessage execute(Update update, String arguments) {
        Long chatId = update.getMessage().getChatId();
        String userMessage = update.getMessage().getText();

        if (!userStep.containsKey(chatId)) {
            userStep.put(chatId, 0);
            userEventData.put(chatId, new EventDto());
            return new SendMessage(chatId.toString(), "Введите название события:");
        }

        int step = userStep.get(chatId);
        EventDto eventDto = userEventData.get(chatId);

        switch (step) {
            case 0:
                eventDto.setTitle(userMessage);
                userStep.put(chatId, 1);
                return new SendMessage(chatId.toString(), "Введите дату события (например, 2024-12-31):");

            case 1:
                eventDto.setDate(userMessage);
                userStep.put(chatId, 2);
                return new SendMessage(chatId.toString(), "Введите место проведения:");

            case 2:
                eventDto.setLocation(userMessage);
                kafkaProducerService.sendEvent(eventDto);

                // Очистка данных
                userStep.remove(chatId);
                userEventData.remove(chatId);

                return new SendMessage(chatId.toString(), "✅ Событие создано и отправлено в систему!");

            default:
                return new SendMessage(chatId.toString(), "Ошибка! Попробуйте снова /create_event");
        }
    }
}
