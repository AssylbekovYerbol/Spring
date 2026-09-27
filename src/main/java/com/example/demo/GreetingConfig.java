package com.example.demo;

import com.example.demo.service.FriendlyGreetingService;
import com.example.demo.service.GreetingService;
import com.example.demo.service.PlainGreetingService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GreetingConfig {

    @Bean
    @ConditionalOnProperty(prefix = "app.feature-x", name = "enabled", havingValue = "true")
    public GreetingService friendlyGreetingService() {
        return new FriendlyGreetingService();
    }

    @Bean
    @ConditionalOnProperty(prefix = "app.feature-x", name = "enabled", havingValue = "false", matchIfMissing = true)
    public GreetingService plainGreetingService() {
        return new PlainGreetingService();
    }

}