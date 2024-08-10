package com.example;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.faces.event.ActionEvent;

@Component("user")
@SuppressWarnings("serial")
public class UserBean implements Serializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserBean.class);
    private User user;

    public User getUser() {
        return user;
    }

    @PostConstruct
    public void init() {
        user = new User(1, "hirakida", "address");
    }

    @Nullable
    public String doAction() {
        LOGGER.info("doAction: {}", user);
        return null;
    }

    public void doActionListener(ActionEvent actionEvent) {
        LOGGER.info("doActionListener: {} {}", user, actionEvent.getPhaseId().getName());
    }
}
