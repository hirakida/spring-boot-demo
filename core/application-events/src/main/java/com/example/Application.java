package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.listener.impl.ApplicationContextInitializedEventListener;
import com.example.listener.impl.ApplicationEnvironmentPreparedEventListener;
import com.example.listener.impl.ApplicationPreparedEventListener;
import com.example.listener.impl.ApplicationStartingEventListener;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.addListeners(new ApplicationStartingEventListener(),
                                       new ApplicationEnvironmentPreparedEventListener(),
                                       new ApplicationContextInitializedEventListener(),
                                       new ApplicationPreparedEventListener());
        springApplication.run(args);
    }
}
