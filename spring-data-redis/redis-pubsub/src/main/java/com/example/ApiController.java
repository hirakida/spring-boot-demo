package com.example;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final ChannelTopic channelTopic;
    private final StringRedisTemplate stringRedisTemplate;

    @PostMapping("/")
    public void publish(@RequestParam String message) {
        stringRedisTemplate.convertAndSend(channelTopic.getTopic(), message);
    }
}
