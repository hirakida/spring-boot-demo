package com.example;

import static com.example.JmsMessageListener.QUEUE1;
import static com.example.JmsMessageListener.QUEUE2;

import java.time.LocalTime;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JmsMessageSender {
    private final JmsMessagingTemplate jmsMessagingTemplate;

    public JmsMessageSender(JmsMessagingTemplate jmsMessagingTemplate) {
        this.jmsMessagingTemplate = jmsMessagingTemplate;
    }

    @Scheduled(fixedDelay = 5000)
    public void sendMessage() {
        jmsMessagingTemplate.convertAndSend(QUEUE1, "Hello! " + LocalTime.now());
        jmsMessagingTemplate.convertAndSend(QUEUE2, new MessageModel("Hello!", LocalTime.now()));
    }
}
