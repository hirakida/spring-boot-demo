package com.example.controller;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("/api/room/{id}")
    public void sendMessage(@PathVariable Long id, @RequestParam String message) {
        log.info("api id={} message={}", id, message);
        simpMessagingTemplate.convertAndSend("/topic/room/" + id, message);
    }
}
