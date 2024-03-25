package com.example.event;

import org.springframework.context.ApplicationEvent;

import com.example.User;

@SuppressWarnings("serial")
public class UserEvent extends ApplicationEvent {

    public UserEvent(User user) {
        super(user);
    }
}
