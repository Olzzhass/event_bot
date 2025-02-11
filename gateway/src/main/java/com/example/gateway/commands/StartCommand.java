package com.example.gateway.commands;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class StartCommand implements Command {
    @Override
    public SendMessage execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        return new SendMessage(chatId, "Привет! Я ваш Telegram-бот 🚀");
    }
}
