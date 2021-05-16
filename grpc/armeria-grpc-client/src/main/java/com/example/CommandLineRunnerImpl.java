package com.example;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.Hello.HelloRequest;
import com.example.HelloServiceGrpc.HelloServiceStub;

import com.linecorp.armeria.client.Clients;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        HelloServiceStub helloService = Clients.newClient("gproto+http://localhost:18080/",
                                                          HelloServiceStub.class);
        HelloStreamObserver streamObserver = new HelloStreamObserver();

        HelloRequest request1 = HelloRequest.newBuilder()
                                            .setName("hirakida1")
                                            .build();
        helloService.helloUnary(request1, streamObserver);
        TimeUnit.SECONDS.sleep(1);

        HelloRequest request2 = HelloRequest.newBuilder()
                                            .setName("hirakida2")
                                            .build();
        helloService.helloServerStreaming(request2, streamObserver);
        TimeUnit.SECONDS.sleep(1);
    }
}
