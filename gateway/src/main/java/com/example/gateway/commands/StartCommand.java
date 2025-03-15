package com.example.gateway.commands;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

@Component
public class StartCommand implements Command {

    @Override
    public SendMessage execute(Update update, String arguments) {
        String chatId = update.getMessage().getChatId().toString();

        String messageText = """
                *Привет! Я ваш Telegram-бот 🚀*
                
                🤖 *Описание:* Этот бот помогает управлять событиями и взаимодействовать с сервисами.
                
                📌 *Доступные команды:*
                ✅ /start - Начать работу с ботом
                ✅ /help - Список команд
                ✅ /subscribe - Подписаться на обновления
                ✅ /settings - Настройки
                """;

        SendMessage message = new SendMessage(chatId, messageText);
        message.setParseMode("Markdown");

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> row = List.of(
                createButton("📜 Помощь", "/help"),
                createButton("⚙️ Настройки", "/settings")
        );
        keyboardMarkup.setKeyboard(List.of(row));
        message.setReplyMarkup(keyboardMarkup);

        return message;
    }

    private InlineKeyboardButton createButton(String text, String command) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(command);
        return button;
    }
}
