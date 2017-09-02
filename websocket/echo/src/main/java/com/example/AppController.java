package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;
import lombok.Data;

@Controller
@AllArgsConstructor
public class AppController {

    final WebSocketHandler webSocketHandler;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/message")
    @ResponseBody
    public void message(@RequestBody TextMessage message) {
        webSocketHandler.sendMessage(message.getMessage());
    }

    @Data
    private static class TextMessage {
        private String message;
    }
}
