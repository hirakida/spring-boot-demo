package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.listener.ApplicationContextInitializedEventListener;
import com.example.listener.ApplicationEnvironmentPreparedEventListener;
import com.example.listener.ApplicationPreparedEventListener;
import com.example.listener.ApplicationStartingEventListener;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.addListeners(new ApplicationStartingEventListener(),
                                       new ApplicationEnvironmentPreparedEventListener(),
                                       new ApplicationContextInitializedEventListener(),
                                       new ApplicationPreparedEventListener());

        try (ConfigurableApplicationContext context = springApplication.run(args)) {
            context.start();
            HelloService service = context.getBean(HelloService.class);
            service.run();
            context.stop();
        }
    }
}
