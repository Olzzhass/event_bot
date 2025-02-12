package com.example.gateway.service;

import com.example.gateway.callbackCommands.CallbackCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CallbackDispatcher {

    private final Map<String, CallbackCommand> callbackCommands;

    public SendMessage dispatch(Update update) {
        String callbackData = update.getCallbackQuery().getData();
        CallbackCommand command = callbackCommands.get(callbackData);
        return command != null
                ? command.execute(update)
                : new SendMessage(update.getCallbackQuery().getMessage().getChatId().toString(), "❌ Неизвестная команда!");
    }
}
