package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.thrift.Hello;

import com.linecorp.armeria.client.Clients;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CommandLineRunnerImpl implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        Hello.Iface hello = Clients.builder("tbinary+http://127.0.0.1:8080/hello")
                                   .build(Hello.Iface.class);
        log.info("{}", hello.hello());
    }
}
