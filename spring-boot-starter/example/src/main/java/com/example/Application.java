package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.hirakida.spring.boot.HelloBean;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private final HelloBean helloBean;

    public Application(HelloBean helloBean) {
        this.helloBean = helloBean;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(helloBean.getMessage());
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
