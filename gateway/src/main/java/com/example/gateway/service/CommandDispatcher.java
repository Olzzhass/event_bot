package com.example.gateway.service;

import com.example.gateway.commands.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommandDispatcher {

    private final Map<String, Command> commandMap;

    public SendMessage dispatch(Update update) {
        String commandText = update.getMessage().getText().split(" ")[0];

        Command command = commandMap.get(commandText);
        if (command != null) {
            return command.execute(update);
        }

        return new SendMessage(update.getMessage().getChatId().toString(), "Неизвестная команда 🤷‍♂️");
    }
}
