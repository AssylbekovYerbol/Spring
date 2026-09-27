package com.example.demo.controller;

import com.example.demo.AppProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final AppProperties appProperties;

    public HelloController(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @GetMapping("/api/hello")
    public String hello(@RequestParam(defaultValue = "world") String name) {
        return "Hello, " + name + "! This is my first Spring Boot REST endpoint.";
    }

    @GetMapping("/api/config")
    public String config() {
        return "feature-x enabled: " + appProperties.isEnabled()
                + ", maxItems: " + appProperties.getMaxItems();
    }

}