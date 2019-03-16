package com.example;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final EmailHelper emailHelper;

    @PostMapping
    public void send(@RequestBody MailRequest request) {
        emailHelper.send(request.getTo(), Map.of("name", request.getName()));
    }

    @Data
    private static class MailRequest {
        private String to;
        private String name;
    }
}
