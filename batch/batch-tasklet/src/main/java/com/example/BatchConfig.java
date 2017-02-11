package com.example;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
                                     log.info("task 1");
                                     return RepeatStatus.FINISHED;
                                 })
                                 .listener(myStepListener)
                                 .build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                                 .tasklet(new MyTasklet())
                                 .listener(myStepListener)
                                 .build();
    }

    public static class MyTasklet implements Tasklet {
        @Override
        public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
            log.info("task 2 {} {}", contribution, chunkContext);
            return RepeatStatus.FINISHED;
        }
    }
}
