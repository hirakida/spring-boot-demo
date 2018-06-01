package com.example

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class Application implements CommandLineRunner {

    @Override
    void run(String... args) throws Exception {
    }

    static void main(String[] args) {
        SpringApplication.run Application, args
    }
}
