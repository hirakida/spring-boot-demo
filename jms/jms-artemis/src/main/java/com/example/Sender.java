package com.example;

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
        jmsMessagingTemplate.convertAndSend(Receiver.TEXT_QUEUE, text);
    }

    public void sendMessage(String text) {
        Message<String> message = MessageBuilder.withPayload(text)
                                                .build();
        jmsMessagingTemplate.send(Receiver.MESSAGE_QUEUE, message);
    }
}
