package com.example.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class WebSocketController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @SubscribeMapping("/subscribe/room/{id}")
    public void subscribe(@DestinationVariable String id, String message,
                          SimpMessageHeaderAccessor headerAccessor) {
        log.info("subscribe id={} message={} headerAccessor={}", id, message, headerAccessor);
        simpMessagingTemplate.convertAndSend("/topic/room/" + id, message);
    }

    @MessageMapping("/message/room/{id}")
    @SendTo("/topic/room/{id}")
    public String message(@DestinationVariable String id, String message) {
        log.info("message={}", message);
        return message;
    }
}
