package com.example;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
public class ApiController {

    final SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("/api/{id}")
    public String sendMessage(@PathVariable Long id, @RequestParam String message) {
        log.info("id={} message={}", id, message);
        simpMessagingTemplate.convertAndSend("/topic/from_api/" + id, message);
        return "ok";
    }
}
