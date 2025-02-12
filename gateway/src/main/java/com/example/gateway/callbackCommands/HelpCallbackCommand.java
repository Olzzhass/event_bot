package com.example.gateway.callbackCommands;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component("/help")
public class HelpCallbackCommand implements CallbackCommand {
    @Override
    public SendMessage execute(Update update) {
        String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
        return new SendMessage(chatId, "ℹ️ *Список команд:*\n✅ `/start` - Начать\n✅ `/help` - Помощь");
    }
}
