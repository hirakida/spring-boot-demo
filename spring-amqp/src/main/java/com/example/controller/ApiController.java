package com.example.controller;

import javax.validation.constraints.NotNull;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.config.RabbitConfig;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final RabbitTemplate rabbitTemplate;

    @PostMapping
    public void send(@RequestBody @Validated Request request) {
        rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_NAME, request.getMessage());
    }

    @Data
    public static class Request {
        private @NotNull String message;
    }
}
