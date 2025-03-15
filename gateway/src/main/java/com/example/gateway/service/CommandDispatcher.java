package com.example.gateway.service;

import com.example.gateway.commands.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Arrays;
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

        String[] parts = messageText.split(" ");
        String commandText = parts[0];
        String arguments = parts.length > 1 ? parts[1] : null;

        System.out.println(Arrays.toString(parts));

        if (commandText.startsWith("/")) {
            Command command = commandMap.get(commandText);
            if (command != null) {
                activeUserCommands.put(chatId, command);
                return command.execute(update, arguments);
            } else {
                return new SendMessage(chatId.toString(), "Неизвестная команда 🤷‍♂️");
            }
        }

        if (activeUserCommands.containsKey(chatId)) {
            return activeUserCommands.get(chatId).execute(update, arguments);
        }

        return new SendMessage(chatId.toString(), "Неизвестная команда 🤷‍♂️");
    }
}
