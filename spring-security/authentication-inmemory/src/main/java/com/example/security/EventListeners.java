package com.example.security;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EventListeners {
    @EventListener
    public void onSuccess(AuthenticationSuccessEvent success) {
        log.info("onSuccess: {}", success.getAuthentication().getPrincipal());
    }

    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent failures) {
        log.info("onFailure: {}", failures.getAuthentication().getPrincipal());
    }
}
