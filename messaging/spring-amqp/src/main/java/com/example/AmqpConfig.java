package com.example;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {
    public static final String TEXT_QUEUE = "text.queue";

    @Bean
    public Queue textQueue() {
        return new Queue(TEXT_QUEUE);
    }
}
