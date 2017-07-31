package com.example;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@AllArgsConstructor
public class MessageSender {

    private final JmsMessagingTemplate jmsMessagingTemplate;

    public void sendText(String text) {
        jmsMessagingTemplate.convertAndSend(AppConfig.TEXT_QUEUE, text);
        log.info("send text {}", text);
    }

    public void sendMessage(String text) {
        Message<String> message = MessageBuilder.withPayload(text)
                                                .build();
        jmsMessagingTemplate.send(AppConfig.MESSAGE_QUEUE, message);
        log.info("send message {}", message);
    }
}
