package com.emse.spring.automacorp.hello;

import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class DummyUserService implements UserService {
    private final ConsoleGreetingService greetingService;
    public DummyUserService(ConsoleGreetingService greetingService) {
        this.greetingService = greetingService;

    }

    public void greetAll(List<String> names) {
        for (String name : names) {
            greetingService.greet(name);
        }
    }
}