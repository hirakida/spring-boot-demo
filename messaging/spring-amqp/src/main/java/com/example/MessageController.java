package com.example;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MessageController {
    private final MessageSender messageSender;

    @PostMapping
    public void send(@RequestBody @Validated Request request) {
        messageSender.send(request.getMessage());
    }

    @Data
    public static class Request {
        @NotNull
        private String message;
    }
}
