package com.example;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import lombok.extern.slf4j.Slf4j;

@ShellComponent
@Slf4j
public class DemoCommands {

    @ShellMethod("hello")
    public void hello(String text) {
        log.info("Hello {}!", text);
    }
}
