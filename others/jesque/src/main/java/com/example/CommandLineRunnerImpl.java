package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import net.greghaines.jesque.Job;

import com.example.job.AppJob;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final JesqueClient jesqueClient;

    @Override
    public void run(String... args) throws Exception {
        jesqueClient.enqueue("test1-1", AppJob.JOB1, 1000);
        jesqueClient.enqueue("test1-2", AppJob.JOB2, 1000);
        jesqueClient.enqueue("test1-3", AppJob.JOB3, 1000);

        // It is executed only once.
        jesqueClient.enqueue("test2-1", AppJob.JOB1, 3000);
        jesqueClient.enqueue("test2-1", AppJob.JOB1, 3000);

        // Remove the job.
        Job job = new Job("Job1", "test3-1");
        jesqueClient.enqueue(job, 5000);
        jesqueClient.dequeue(job);

        jesqueClient.enqueue("test3-2", AppJob.JOB2, 5000);
        Thread.sleep(3000);
        // Only this job is executed.
        jesqueClient.enqueue("test3-2", AppJob.JOB2, 5000);
    }
}
