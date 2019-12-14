package com.example.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class WebSocketController {
    private static final long ROOM_ID = 1;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("roomId", ROOM_ID);
        return "index";
    }

    @MessageMapping("/message/room/{id}")
    @SendTo("/topic/room/{id}")
    public Payload message(@DestinationVariable String id, Payload payload) {
        log.info("id={} payload={}", id, payload);
        if (StringUtils.isEmpty(StringUtils.trim(payload.getMessage()))) {
            throw new IllegalArgumentException("The message is empty.");
        }
        return payload;
    }

    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public Error handleException(Throwable e) {
        return new Error(e.getMessage());
    }

    @Data
    public static class Payload {
        private String userName;
        private String message;
    }

    @Data
    @AllArgsConstructor
    public static class Error {
        private String message;
    }
}
