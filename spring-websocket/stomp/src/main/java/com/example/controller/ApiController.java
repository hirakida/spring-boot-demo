package com.example.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
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
    private final SimpUserRegistry simpUserRegistry;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("/api/users")
    public List<String> getUsers() {
        return simpUserRegistry.getUsers().stream()
                               .map(SimpUser::getName)
                               .collect(Collectors.toList());
    }

    @GetMapping("/api/room/{id}")
    public void sendMessage(@PathVariable Long id, @RequestParam String message) {
        log.info("id={} message={}", id, message);
        simpMessagingTemplate.convertAndSend("/topic/room/" + id, message);
    }
}
