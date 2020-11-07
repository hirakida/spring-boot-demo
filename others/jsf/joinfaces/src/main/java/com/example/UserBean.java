package com.example;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Component
@ViewScoped
@Slf4j
@SuppressWarnings("serial")
public class UserBean implements Serializable {
    @Autowired
    private UserRepository userRepository;
    @Getter
    private User user;

    @PostConstruct
    public void init() {
        user = userRepository.findById(1)
                             .orElse(new User());
    }

    @Nullable
    public String doAction() {
        log.info("doAction: {}", user);
        return null;
    }

    public void doActionListener(ActionEvent actionEvent) {
        log.info("doActionListener: {} {}", user, actionEvent.getPhaseId().getName());
        userRepository.save(user);
    }
}
