package com.example;

import static com.example.Constants.DESTINATION1;
import static com.example.Constants.DESTINATION2;
import static com.example.Constants.DESTINATION3;

import org.apache.activemq.ScheduledMessage;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JmsClient {
    private final JmsMessagingTemplate jmsMessagingTemplate;
    private final JmsTemplate jmsTemplate;

    public void sendMessage1(String data) {
        jmsMessagingTemplate.convertAndSend(DESTINATION1, data);
    }

    public void sendMessage2(String data) {
        Message<String> message = MessageBuilder.withPayload(data).build();
        jmsMessagingTemplate.send(DESTINATION2, message);
    }

    public void sendDelayedMessage(String data, long milliseconds) {
        jmsTemplate.convertAndSend(DESTINATION3, data, message -> {
            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, milliseconds);
            return message;
        });
    }
}
