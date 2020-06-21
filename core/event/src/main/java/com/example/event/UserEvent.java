package com.example.event;

import org.springframework.context.ApplicationEvent;

import com.example.model.User;

@SuppressWarnings("serial")
public class UserEvent extends ApplicationEvent {

    public UserEvent(User user) {
        super(user);
    }
}
