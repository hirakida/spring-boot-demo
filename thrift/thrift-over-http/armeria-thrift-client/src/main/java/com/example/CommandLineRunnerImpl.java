package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.hello.Hello;

import com.linecorp.armeria.client.Clients;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(CommandLineRunnerImpl.class);

    @Override
    public void run(String... args) throws Exception {
        Hello.Iface client = Clients.newClient("tbinary+http://localhost:8080/hello", Hello.Iface.class);
        log.info("{}", client.hello("hirakida"));
    }
}
