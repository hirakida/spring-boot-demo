package com.example;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableTask
@EnableBatchProcessing
@EnableScheduling
@SpringBootApplication
public class TaskBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskBatchApplication.class, args);
    }
}
