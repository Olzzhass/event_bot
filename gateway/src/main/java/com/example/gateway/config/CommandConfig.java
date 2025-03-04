package com.example.gateway.config;

import com.example.gateway.commands.Command;
import jakarta.annotation.Priority;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class CommandConfig {

    @Bean
    public Map<String, Command> commandMap(Map<String, Command> commands) {
        System.out.println(commands);
        return commands.entrySet().stream()
                .collect(Collectors.toMap(entry -> "/" + entry.getKey().replace("Command", "")
                        .toLowerCase(), Map.Entry::getValue));
    }
}
