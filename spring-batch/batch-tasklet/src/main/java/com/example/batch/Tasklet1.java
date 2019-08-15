package com.example.batch;

import java.util.Random;
import java.util.stream.IntStream;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import com.example.core.User;
import com.example.core.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class Tasklet1 implements Tasklet {
    private final UserRepository userRepository;
    private final ScopeBean jobScopeBean;
    private final ScopeBean stepScopeBean;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("{} {}", jobScopeBean, stepScopeBean);

        IntStream.rangeClosed(1, 5)
                 .forEach(i -> {
                     User user = new User();
                     user.setName("user" + new Random().nextInt());
                     user.setEnabled(false);
                     userRepository.saveAndFlush(user);
                 });

        userRepository.findAll()
                      .forEach(user -> log.info("{}", user));
        return RepeatStatus.FINISHED;
    }
}
