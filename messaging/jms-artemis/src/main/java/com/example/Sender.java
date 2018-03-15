package com.example;

import static com.example.Receiver.MESSAGE_QUEUE;
import static com.example.Receiver.TEXT_QUEUE;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Sender {

    private final JmsMessagingTemplate jmsMessagingTemplate;

    public void sendText(String text) {
        jmsMessagingTemplate.convertAndSend(TEXT_QUEUE, text);
    }

    public void sendMessage(String text) {
        Message<String> message = MessageBuilder.withPayload(text)
                                                .build();
        jmsMessagingTemplate.send(MESSAGE_QUEUE, message);
    }
}
