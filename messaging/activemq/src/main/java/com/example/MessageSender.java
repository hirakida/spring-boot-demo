package com.example;

import javax.jms.Queue;

import org.apache.activemq.ScheduledMessage;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
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
    private final JmsTemplate jmsTemplate;
    private final Queue queue;

    public void sendText(String text) {
        jmsMessagingTemplate.convertAndSend(queue, text);
        log.info("send text {}", text);
    }

    public void sendMessage(String text) {
        Message<String> message = MessageBuilder.withPayload(text)
                                                .build();
        jmsMessagingTemplate.send(AppConfig.MESSAGE_QUEUE, message);
        log.info("send message {}", message);
    }

    public void sendMessage(String text, long millisecond) {
        jmsTemplate.convertAndSend(AppConfig.DELAYED_QUEUE, text, message -> {
            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, millisecond);
            return message;
        });
        log.info("send message to delayed queue {}", text);
    }
}
