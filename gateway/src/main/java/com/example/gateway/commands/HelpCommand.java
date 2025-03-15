package com.example.gateway.commands;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class HelpCommand implements Command {
    @Override
    public SendMessage execute(Update update, String arguments) {
        String chatId = update.getMessage().getChatId().toString();
        return new SendMessage(chatId, "Доступные команды:\n/start - Приветствие\n/help - Список команд");
    }
}
