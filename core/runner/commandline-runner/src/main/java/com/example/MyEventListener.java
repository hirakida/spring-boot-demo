package com.example;

import org.springframework.boot.ExitCodeEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MyEventListener {
    @EventListener(ExitCodeEvent.class)
    public void exitCodeEvent(ExitCodeEvent event) {
        System.out.println("exitCode: " + event.getExitCode());
    }
}
