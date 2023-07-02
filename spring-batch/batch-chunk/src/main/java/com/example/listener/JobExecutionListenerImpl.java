package com.example.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JobExecutionListenerImpl implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("BeforeJob {}", jobExecution);
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("AfterJob {}", jobExecution);
    }
}
