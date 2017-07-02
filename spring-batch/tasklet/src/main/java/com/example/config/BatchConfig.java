package com.example.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.batch.MyJobListener;
import com.example.batch.MyStepListener;
import com.example.batch.MyTasklet;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
@Slf4j
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final MyJobListener myJobListener;

    private final MyStepListener myStepListener;

    @Bean
    public Job job1() {
        return jobBuilderFactory.get("job1")
                                .incrementer(new RunIdIncrementer())
                                .listener(myJobListener)
                                .start(step1())
                                .next(step2())
                                .build();
    }

    @Bean
    public Job job2() {
        return jobBuilderFactory.get("job2")
                                .incrementer(new RunIdIncrementer())
                                .listener(myJobListener)
                                .start(step1())
                                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                                 .tasklet((stepContribution, chunkContext) -> {
                                     log.info("task1 {} {}", stepContribution, chunkContext);
                                     return RepeatStatus.FINISHED;
                                 })
                                 .listener(myStepListener)
                                 .build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                                 .tasklet(new MyTasklet("task2"))
                                 .listener(myStepListener)
                                 .build();
    }
}
