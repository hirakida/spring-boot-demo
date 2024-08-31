package com.example;

import static com.example.JmsMessageListener.DESTINATION1;
import static com.example.JmsMessageListener.DESTINATION2;
import static com.example.JmsMessageListener.DESTINATION3;

import org.apache.activemq.ScheduledMessage;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JmsMessageSender {
    private final JmsMessagingTemplate jmsMessagingTemplate;
    private final JmsTemplate jmsTemplate;

    public JmsMessageSender(JmsMessagingTemplate jmsMessagingTemplate, JmsTemplate jmsTemplate) {
        this.jmsMessagingTemplate = jmsMessagingTemplate;
        this.jmsTemplate = jmsTemplate;
    }

    @Scheduled(fixedDelay = 5000)
    public void sendMessages() {
        jmsMessagingTemplate.convertAndSend(DESTINATION1, "Hello!");

        jmsMessagingTemplate.send(DESTINATION2, MessageBuilder.withPayload(new MessageModel("Hello!")).build());

        jmsTemplate.convertAndSend(DESTINATION3, "Hello!", message -> {
            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, 2000);
            return message;
        });
    }
}
