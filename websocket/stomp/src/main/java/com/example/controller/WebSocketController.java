package com.example.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class WebSocketController {

    @MessageMapping("/room/{id}")
    @SendTo("/topic/room/{id}")
    public Payload message(@DestinationVariable String id, Payload payload) {
        log.info("id={} payload={}", id, payload);
        return payload;
    }

    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public String handleException(Throwable e) {
        return e.getMessage();
    }

    @Data
    public static class Payload {
        private String username;
        private String message;
    }
}
