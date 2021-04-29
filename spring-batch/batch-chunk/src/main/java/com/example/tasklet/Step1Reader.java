package com.example.tasklet;

import java.util.Iterator;
import java.util.List;

import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterRead;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeRead;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.example.entity.User;
import com.example.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class Step1Reader implements ItemStreamReader<User> {
    private final UserRepository userRepository;
    private Iterator<User> users;

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        JobParameters jobParameters = stepExecution.getJobExecution().getJobParameters();
        JobParameter parameter = jobParameters.getParameters().get("time");
        log.info("BeforeStep {} time={}", stepExecution, parameter.getValue());
    }

    @AfterStep
    public void afterStep(StepExecution stepExecution) {
        log.info("AfterStep {}", stepExecution);
    }

    @BeforeRead
    public void beforeRead() {
        log.info("BeforeRead");
    }

    @AfterRead
    public void afterRead(User user) {
        log.info("AfterRead {}", user);
    }

    @Override
    public void open(ExecutionContext executionContext) {
        List<User> users = userRepository.findByEnabledFalse();
        log.info("open: {}", users);
        this.users = users.iterator();
    }

    @Nullable
    @Override
    public User read() throws Exception {
        if (!users.hasNext()) {
            log.info("read: null");
            return null;
        }

        User user = users.next();
        log.info("read: {}", user);
        return user;
    }

    @Override
    public void update(ExecutionContext executionContext) {
        log.info("update");
    }

    @Override
    public void close() {
        log.info("close");
    }
}
