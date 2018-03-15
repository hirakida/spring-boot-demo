package com.example;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final Sender sender;

    @PostMapping("/text")
    public void sendText(@RequestBody Request request) {
        sender.sendText(request.getText());
    }

    @PostMapping("/message")
    public void sendMessage(@RequestBody Request request) {
        sender.sendMessage(request.getText());
    }

    @Data
    public static class Request {
        private String text;
    }
}
