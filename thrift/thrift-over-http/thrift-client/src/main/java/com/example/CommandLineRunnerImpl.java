package com.example;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.THttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.hello.Hello;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(CommandLineRunnerImpl.class);

    @Override
    public void run(String... args) throws Exception {
        THttpClient transport = new THttpClient("http://localhost:8080/hello");
        TProtocol protocol = new TBinaryProtocol(transport);
        Hello.Iface client = new Hello.Client(protocol);
        log.info("{}", client.hello("hirakida"));
        transport.close();
    }
}
