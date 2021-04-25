package com.example;

import static com.example.AmqpConfig.QUEUE_NAME;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class Application {
    private final RabbitTemplate rabbitTemplate;

    @Scheduled(fixedDelay = 5000)
    public void sendMessage() {
        rabbitTemplate.convertAndSend(QUEUE_NAME, "Hello!");
    }

    @RabbitListener(queues = QUEUE_NAME)
    public void receiveMessage(@Payload String data) {
        log.info(data);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
