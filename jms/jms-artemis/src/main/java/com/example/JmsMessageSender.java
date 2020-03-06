package com.example;

import static com.example.Constants.DESTINATION1;
import static com.example.Constants.DESTINATION2;

import java.time.LocalDateTime;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JmsMessageSender {
    private final JmsMessagingTemplate jmsMessagingTemplate;

    public void sendMessage1(String text) {
        jmsMessagingTemplate.convertAndSend(DESTINATION1, text);
    }

    public void sendMessage2(String text) {
        LocalDateTime now = LocalDateTime.now();
        Message<String> message = MessageBuilder.withPayload(text)
                                                .setHeader("datetime", now.toString())
                                                .build();
        jmsMessagingTemplate.send(DESTINATION2, message);
    }
}
