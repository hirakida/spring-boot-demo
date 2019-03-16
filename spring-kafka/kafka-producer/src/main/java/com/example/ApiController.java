package com.example;

import java.util.Objects;
import java.util.Random;

import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;
import com.example.producer.MessageSender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiController {
    private final MessageSender messageSender;

    @PostMapping("/send")
    public Response send(@Validated @RequestBody Request request) {
        User user = new User(new Random().nextLong(), request.getName());
        messageSender.sendDefault(request.getKey(), user)
                     .addCallback(result -> log.info("callback: record={}",
                                                     Objects.requireNonNull(result).getProducerRecord()),
                                  e -> log.error("callback: error", e));
        return new Response(request.getKey(), user);
    }

    @Data
    public static class Request {
        private @NotEmpty String key;
        private @NotEmpty String name;
    }

    @Data
    @AllArgsConstructor
    public static class Response {
        private String key;
        private User user;
    }
}
