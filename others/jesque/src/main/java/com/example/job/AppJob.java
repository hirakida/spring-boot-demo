package com.example.job;

import java.util.Optional;
import java.util.stream.Stream;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AppJob {
    JOB1("job1", Job1.class),
    JOB2("job2", Job2.class),
    JOB3("job3", Job3.class);

    private final String name;
    private final Class<? extends Runnable> job;

    public static Optional<AppJob> from(String name) {
        return Stream.of(values())
                     .filter(value -> name.equals(value.getName()))
                     .findFirst();
    }
}
