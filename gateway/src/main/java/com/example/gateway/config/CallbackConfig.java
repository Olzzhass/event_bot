package com.example.gateway.config;

import com.example.gateway.callbackCommands.CallbackCommand;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class CallbackConfig {
    @Bean
    public Map<String, CallbackCommand> callbackCommandMap(Map<String, CallbackCommand> commands) {
        System.out.println(commands);
        return commands.entrySet().stream()
                .collect(Collectors.toMap(entry -> entry.getKey().replace("CallbackCommand", "").toLowerCase(), Map.Entry::getValue));
    }
}
