package com.example;

import java.time.LocalDateTime;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class BatchConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job initialJob() {
        return jobBuilderFactory.get("initialJob")
                                .start(step("step1"))
                                .build();
    }

    @Bean
    public Job scheduledJob() {
        return jobBuilderFactory.get("scheduledJob")
                                .start(step("step1"))
                                .next(step("step2"))
                                .build();
    }

    private Step step(String name) {
        return stepBuilderFactory.get(name)
                                 .tasklet(tasklet())
                                 .build();
    }

    private static Tasklet tasklet() {
        return (contribution, chunkContext) -> {
            log.info("tasklet {}", LocalDateTime.now());
            return RepeatStatus.FINISHED;
        };
    }
}
