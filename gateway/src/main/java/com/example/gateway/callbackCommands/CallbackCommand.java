package com.example.gateway.callbackCommands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface CallbackCommand {
    SendMessage execute(Update update);
}
