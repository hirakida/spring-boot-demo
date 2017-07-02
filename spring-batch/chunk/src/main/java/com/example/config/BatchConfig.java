package com.example.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.batch.MyItemProcessor;
import com.example.batch.MyItemReader;
import com.example.batch.MyItemWriter;
import com.example.batch.MyJobListener;
import com.example.batch.MyStepListener;
import com.example.domain.Account;

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
    public Job job() {
        return jobBuilderFactory.get("myJob")
                                .incrementer(new RunIdIncrementer())
                                .listener(myJobListener)
                                .start(step())
                                .build();
    }

    @Bean
    public Step step() {
        return stepBuilderFactory.get("myStep")
                .<Account, Account>chunk(1)
                .reader(myItemReader)
                .processor(myItemProcessor)
                .writer(myItemWriter)
                .listener(myStepListener)
                .build();
    }
}
