package com.example;

import java.time.LocalDateTime;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class Commands {
    @ShellMethod("hello")
    public void hello(String message) {
        System.out.println(message);
    }

    @ShellMethod("now")
    public void now() {
        System.out.println(LocalDateTime.now());
    }
}
