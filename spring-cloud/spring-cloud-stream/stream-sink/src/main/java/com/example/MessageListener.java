package com.example;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MessageListener {

    @StreamListener(Sink.INPUT)
    public void listener(Message<String> message) {
        log.info("payload: {} headers: {}", message.getPayload(), message.getHeaders());
    }
}
