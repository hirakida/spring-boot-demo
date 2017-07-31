package com.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

@Configuration
@EnableJms
public class AppConfig {
    public static final String TEXT_QUEUE = "text.queue";
    public static final String MESSAGE_QUEUE = "message.queue";
}
