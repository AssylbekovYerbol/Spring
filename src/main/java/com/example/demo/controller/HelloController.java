package com.example.demo.controller;

import com.example.demo.AppProperties;
import com.example.demo.service.GreetingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final AppProperties appProperties;
    private final GreetingService greetingService;

    public HelloController(AppProperties appProperties, GreetingService greetingService) {
        this.appProperties = appProperties;
        this.greetingService = greetingService;
    }

    @GetMapping("/api/hello")
    public String hello(@RequestParam(defaultValue = "world") String name) {
        return greetingService.greet(name);
    }

    @GetMapping("/api/config")
    public String config() {
        return "feature-x enabled: " + appProperties.isEnabled()
                + ", maxItems: " + appProperties.getMaxItems();
    }

}