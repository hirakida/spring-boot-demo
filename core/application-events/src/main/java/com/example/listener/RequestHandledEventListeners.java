package com.example.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.RequestHandledEvent;
import org.springframework.web.context.support.ServletRequestHandledEvent;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RequestHandledEventListeners {
    @EventListener
    public void handleEvent(RequestHandledEvent event) {
        log.info("RequestHandledEvent={}", event.getSource());
    }

    @EventListener
    public void handleEvent(ServletRequestHandledEvent event) {
        log.info("ServletRequestHandledEvent={}", event.getSource());
    }
}
