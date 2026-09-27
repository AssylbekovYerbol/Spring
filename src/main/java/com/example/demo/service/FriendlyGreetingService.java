package com.example.demo.service;

public class FriendlyGreetingService implements GreetingService {

    @Override
    public String greet(String name) {
        return "Hey there, " + name + "! Great to see you here, hope you're having an awesome day!";
    }

}