package com.example;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
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

    private final MyItemReader myItemReader;

    private final MyItemProcessor myItemProcessor;

    private final MyItemWriter myItemWriter;

    private final MyJobListener myJobListener;

    private final MyStepListener myStepListener;

    @Bean
    public Job job1() {
        return jobBuilderFactory.get("myJob")
                                .incrementer(new RunIdIncrementer())
                                .listener(myJobListener)
                                .start(step1())
                                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<String, String>chunk(1)
                .reader(myItemReader)
                .processor(myItemProcessor)
                .writer(myItemWriter)
                .listener(myStepListener)
                .build();
    }
}
