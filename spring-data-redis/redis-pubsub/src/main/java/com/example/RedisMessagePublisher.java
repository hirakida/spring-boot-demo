package com.example;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RedisMessagePublisher {
    private final ChannelTopic channelTopic;
    private final StringRedisTemplate stringRedisTemplate;

    public void publish(String message) {
        stringRedisTemplate.convertAndSend(channelTopic.getTopic(), message);
    }
}
