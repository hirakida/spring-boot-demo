package com.example.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

import com.example.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class JobExecutionListenerImpl extends JobExecutionListenerSupport {
    private final UserRepository userRepository;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("beforeJob {}", jobExecution);
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("afterJob {}", jobExecution);
        userRepository.deleteAll();
    }
}
