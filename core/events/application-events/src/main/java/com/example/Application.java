package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.listeners.ApplicationContextInitializedEventListener;
import com.example.listeners.ApplicationEnvironmentPreparedEventListener;
import com.example.listeners.ApplicationPreparedEventListener;
import com.example.listeners.ApplicationStartingEventListener;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class Application implements CommandLineRunner {
    public static void main(String[] args) {
        final SpringApplication application = new SpringApplication(Application.class);
        application.addListeners(new ApplicationStartingEventListener(),
                                 new ApplicationEnvironmentPreparedEventListener(),
                                 new ApplicationContextInitializedEventListener(),
                                 new ApplicationPreparedEventListener());

        try (ConfigurableApplicationContext context = application.run(args)) {
            context.start();
            final HelloService service = context.getBean(HelloService.class);
            service.run();
            context.stop();
        }
    }

    @Override
    public void run(String... args) {
        log.info("CommandLineRunner");
    }
}
