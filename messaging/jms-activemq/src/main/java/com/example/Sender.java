package com.example;

import javax.jms.Queue;

import org.apache.activemq.ScheduledMessage;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class Sender {

    private final JmsMessagingTemplate jmsMessagingTemplate;
    private final JmsTemplate jmsTemplate;
    private final Queue textQueue;

    public void sendText(String data) {
        // same
        jmsMessagingTemplate.convertAndSend(AppConfig.TEXT_QUEUE_NAME, data);
        jmsMessagingTemplate.convertAndSend(textQueue, data);
    }

    public void sendMessage(String data) {
        Message<String> message = MessageBuilder.withPayload(data)
                                                .build();
        jmsMessagingTemplate.send(AppConfig.MESSAGE_QUEUE, message);
    }

    public void sendMessage(String data, long millisecond) {
        jmsTemplate.convertAndSend(AppConfig.DELAYED_QUEUE, data, message -> {
            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, millisecond);
            return message;
        });
        log.info("send message to delayed queue {}", data);
    }
}
