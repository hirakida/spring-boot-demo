package com.example.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.batch.ItemProcessorImpl;
import com.example.batch.ItemReaderImpl;
import com.example.batch.ItemWriterImpl;
import com.example.batch.JobExecutionListenerImpl;
import com.example.batch.StepExecutionListenerImpl;
import com.example.core.Member;
import com.example.core.User;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final ItemReaderImpl itemReaderImpl;
    private final ItemProcessorImpl itemProcessorImpl;
    private final ItemWriterImpl itemWriterImpl;
    private final JobExecutionListenerImpl jobExecutionListenerImpl;
    private final StepExecutionListenerImpl stepExecutionListenerImpl;

    @Bean
    public Job job1() {
        return jobBuilderFactory.get("job1")
                                .incrementer(new RunIdIncrementer())
                                .listener(jobExecutionListenerImpl)
                                .start(step1())
                                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<User, Member>chunk(1)
                .reader(itemReaderImpl)
                .processor(itemProcessorImpl)
                .writer(itemWriterImpl)
                .listener(stepExecutionListenerImpl)
                .build();
    }
}
