package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.listeners.ApplicationContextInitializedEventListener;
import com.example.listeners.ApplicationEnvironmentPreparedEventListener;
import com.example.listeners.ApplicationPreparedEventListener;
import com.example.listeners.ApplicationStartingEventListener;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        application.addListeners(new ApplicationStartingEventListener(),
                                 new ApplicationEnvironmentPreparedEventListener(),
                                 new ApplicationContextInitializedEventListener(),
                                 new ApplicationPreparedEventListener());

        try (ConfigurableApplicationContext context = application.run(args)) {
            context.start();
            HelloService service = context.getBean(HelloService.class);
            service.run();
            context.stop();
        }
    }
}
