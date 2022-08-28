package com.example;

import static com.example.JmsConfig.DESTINATION1;
import static com.example.JmsConfig.DESTINATION2;
import static com.example.JmsConfig.DESTINATION3;

import java.time.LocalDateTime;

import org.apache.activemq.ScheduledMessage;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.example.model.Greeting;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JmsClient {
    private final JmsMessagingTemplate jmsMessagingTemplate;
    private final JmsTemplate jmsTemplate;

    public void sendMessage(String data) {
        jmsMessagingTemplate.convertAndSend(DESTINATION1, data);
    }

    public void sendMessage(Greeting greeting) {
        LocalDateTime now = LocalDateTime.now();
        Message<Greeting> message = MessageBuilder.withPayload(greeting)
                                                  .setHeader("datetime", now.toString())
                                                  .build();
        jmsMessagingTemplate.send(DESTINATION2, message);
    }

    public void sendDelayedMessage(String data, long milliseconds) {
        jmsTemplate.convertAndSend(DESTINATION3, data, message -> {
            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, milliseconds);
            return message;
        });
    }
}
