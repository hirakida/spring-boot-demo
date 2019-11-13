package com.example;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    @Bean
    @InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(fixedRate = "5000"))
    public MessageSource<String> dateTimeSource() {
        return () -> MessageBuilder.withPayload(LocalDateTime.now().toString())
                                   .setHeader("LocalDate", LocalDate.now().toString())
                                   .build();
    }
}
