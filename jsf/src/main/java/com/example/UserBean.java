package com.example;

import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.lang.Nullable;
import org.springframework.web.context.annotation.SessionScope;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Named
@SessionScope
@Slf4j
public class UserBean {
    @Getter
    private final User user;

    @Inject
    public UserBean() {
        user = new User(1, "first1", "last1");
        log.info("{}", user);
    }

    @Nullable
    public String doAction() {
        log.info("doAction: {}", user);
        return null;
    }

    public void doActionListener(ActionEvent actionEvent) {
        log.info("{}", actionEvent.getPhaseId().getName());
    }
}
