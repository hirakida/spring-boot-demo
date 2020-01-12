package com.example.config;

import net.greghaines.jesque.worker.DefaultExceptionHandler;
import net.greghaines.jesque.worker.JobExecutor;
import net.greghaines.jesque.worker.RecoveryStrategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WorkerExceptionHandler extends DefaultExceptionHandler {

    @Override
    public RecoveryStrategy onException(JobExecutor jobExecutor, Exception exception, String curQueue) {
        log.error("onException {} {} {}", jobExecutor, exception, curQueue);
        return super.onException(jobExecutor, exception, curQueue);
    }
}
