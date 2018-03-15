package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.batch.JobService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class JobController {

    private final JobService jobService;

    @GetMapping("/job1")
    public String job1() {
        jobService.job1();
        return "ok";
    }

    @GetMapping("/job2")
    public String job2() {
        jobService.job2();
        return "ok";
    }

    @GetMapping("/job3")
    public String job3() {
        jobService.job3();
        return "ok";
    }
}
