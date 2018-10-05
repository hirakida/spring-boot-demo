package com.example.tasklet;

import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import com.example.entity.User;
import com.example.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class Tasklet2 implements Tasklet {

    private final UserRepository userRepository;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        userRepository.findAll()
                      .forEach(user -> {
                          user.setEnabled(true);
                          userRepository.saveAndFlush(user);
                      });

        List<User> users = userRepository.findAll();
        users.forEach(user -> log.info("{}", user));
        return RepeatStatus.FINISHED;
    }
}
