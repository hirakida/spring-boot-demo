package com.example.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.example.UserController;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(UserController.class);
    }
}
