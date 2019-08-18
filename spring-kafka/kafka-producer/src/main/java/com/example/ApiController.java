package com.example;

import java.util.Random;

import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiController {
    private final MessageSender messageSender;

    @PostMapping("/")
    public void send(@Validated @RequestBody Request request) {
        User user = new User(new Random().nextLong(), request.getName());
        messageSender.sendDefault(request.getKey(), user);
    }

    @Data
    public static class Request {
        private @NotEmpty String key;
        private @NotEmpty String name;
    }
}
