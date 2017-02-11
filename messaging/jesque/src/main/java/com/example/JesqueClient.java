package com.example;

import static com.example.JesqueConfig.QUEUE_NAME;

import java.time.Clock;

import org.springframework.stereotype.Component;

import net.greghaines.jesque.Job;
import net.greghaines.jesque.client.Client;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class JesqueClient {

    private final Client client;

    private Clock clock;

    public void enqueue(String message, String jobName) {
        Job job = new Job(jobName, message);
        long future = clock.millis() + 1000;
        client.delayedEnqueue(QUEUE_NAME, job, future);
        log.info("sent {}", message);
    }
}
