package com.example.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StepExecutionListenerImpl implements StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("BeforeStep {}", stepExecution);
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("AfterStep {}", stepExecution);
        return stepExecution.getExitStatus();
    }
}
