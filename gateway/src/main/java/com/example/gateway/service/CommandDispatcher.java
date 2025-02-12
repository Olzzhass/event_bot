package com.example.gateway.service;

import com.example.gateway.commands.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommandDispatcher {

    private final Map<String, Command> commandMap;
    private final Map<Long, Command> activeUserCommands = new HashMap<>();

    public SendMessage dispatch(Update update) {
        String messageText = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();

        if (messageText.startsWith("/")) {
            Command command = commandMap.get(messageText);
            if (command != null) {
                activeUserCommands.put(chatId, command);
                return command.execute(update);
            } else {
                return new SendMessage(chatId.toString(), "Неизвестная команда 🤷‍♂️");
            }
        }

        if (activeUserCommands.containsKey(chatId)) {
            return activeUserCommands.get(chatId).execute(update);
        }

        return new SendMessage(chatId.toString(), "Неизвестная команда 🤷‍♂️");
    }
}
