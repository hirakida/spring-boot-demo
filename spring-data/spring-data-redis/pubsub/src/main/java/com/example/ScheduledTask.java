package com.example;

import java.time.LocalDateTime;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ScheduledTask {
    private final ChannelTopic channelTopic;
    private final StringRedisTemplate stringRedisTemplate;

    @Scheduled(fixedRate = 5000)
    public void task() {
        String message = "Hello! " + LocalDateTime.now();
        stringRedisTemplate.convertAndSend(channelTopic.getTopic(), message);
    }
}
