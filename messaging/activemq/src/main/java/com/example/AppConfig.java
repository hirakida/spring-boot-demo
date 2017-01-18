package com.example;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

@Configuration
@EnableJms
public class AppConfig {

    public static final String TEXT_NAME = "text.queue";
    public static final String MESSAGE_QUEUE = "message.queue";

    @Bean
    public Queue queue() {
        return new ActiveMQQueue(TEXT_NAME);
    }
}
