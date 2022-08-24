package com.example;

import java.nio.charset.StandardCharsets;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.lang.Nullable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageListenerImpl implements MessageListener {
    @Override
    public void onMessage(Message message, @Nullable byte[] pattern) {
        final String channel = new String(message.getChannel(), StandardCharsets.UTF_8);
        final String body = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("channel={} body={}", channel, body);
    }
}
