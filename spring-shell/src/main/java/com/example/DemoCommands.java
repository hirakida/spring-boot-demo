package com.example;

import java.time.LocalDateTime;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import lombok.extern.slf4j.Slf4j;

@ShellComponent
@Slf4j
public class DemoCommands {

    @ShellMethod("demo")
    public void demo(String message) {
        log.info("{}", message);
    }

    @ShellMethod("now")
    public void now() {
        log.info("{}", LocalDateTime.now());
    }
}
