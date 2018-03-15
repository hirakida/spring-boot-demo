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

    @PostMapping("/send")
    public void send(@RequestBody Request request) {
        sender.send(request.getText());
    }

    @Data
    public static class Request {
        private String text;
    }
}
