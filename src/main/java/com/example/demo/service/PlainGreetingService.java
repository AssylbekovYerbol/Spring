package com.example.demo.service;

public class PlainGreetingService implements GreetingService {

    @Override
    public String greet(String name) {
        return "Hello, " + name + ".";
    }

}