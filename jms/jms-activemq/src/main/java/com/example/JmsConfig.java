package com.example;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

@Configuration
@EnableJms
public class JmsConfig {

    public static final String TEXT_QUEUE = "text.queue";
    public static final String MESSAGE_QUEUE = "message.queue";
    public static final String DELAYED_QUEUE = "delayed.queue";

    @Bean
    public Queue textQueue() {
        return new ActiveMQQueue(TEXT_QUEUE);
    }
}
