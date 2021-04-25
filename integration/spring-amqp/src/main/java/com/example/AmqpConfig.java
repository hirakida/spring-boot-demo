package com.example;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {
    public static final String QUEUE_NAME = "text.queue";

    @Bean
    public Queue textQueue() {
        return new Queue(QUEUE_NAME);
    }
}
