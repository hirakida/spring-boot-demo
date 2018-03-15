package com.example;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.batch.JobService;
import com.example.entity.Member;
import com.example.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableJpaAuditing
@RequiredArgsConstructor
@Slf4j
public class ChunkApplication {

    private final JobService jobService;
    private final MemberRepository memberRepository;

    @Scheduled(fixedRate = 10000)
    public void job1() {
        jobService.job1();
        List<Member> members = memberRepository.findAll();
        log.info("{}", members);
    }

    public static void main(String[] args) {
        SpringApplication.run(ChunkApplication.class, args);
    }
}
