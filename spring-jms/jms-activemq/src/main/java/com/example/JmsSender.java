package com.example;

import static com.example.MyJmsListener.DELAYED_MESSAGE_QUEUE;
import static com.example.MyJmsListener.MESSAGE_QUEUE;
import static com.example.MyJmsListener.TEXT_QUEUE;

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
public class JmsSender {
    private final JmsMessagingTemplate jmsMessagingTemplate;
    private final JmsTemplate jmsTemplate;

    public void sendText(String data) {
        jmsMessagingTemplate.convertAndSend(TEXT_QUEUE, data);
    }

    public void sendMessage(String data) {
        Message<String> message = MessageBuilder.withPayload(data).build();
        jmsMessagingTemplate.send(MESSAGE_QUEUE, message);
    }

    public void sendMessage(String data, long millisecond) {
        jmsTemplate.convertAndSend(DELAYED_MESSAGE_QUEUE, data, message -> {
            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, millisecond);
            return message;
        });
        log.info("Send to delayed message queue. {}", data);
    }
}
