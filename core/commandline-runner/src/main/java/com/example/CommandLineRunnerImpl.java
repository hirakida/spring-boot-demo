package com.example;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeEvent;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner, ExitCodeGenerator {
    private static final int EXIT_SUCCESS = 0;
    private static final int EXIT_FAILURE = -1;
    private int exitCode = EXIT_SUCCESS;

    @Override
    public void run(String... args) {
        if (args.length == 0) {
            exitCode = EXIT_FAILURE;
            return;
        }

        System.out.println("args: " + Arrays.toString(args));
    }

    @Override
    public int getExitCode() {
        return exitCode;
    }

    @EventListener(ExitCodeEvent.class)
    public void exitCodeEvent(ExitCodeEvent event) {
        System.out.println("ExitCodeEvent: " + event.getExitCode());
    }
}
