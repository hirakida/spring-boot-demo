package com.example;

import java.util.concurrent.TimeUnit;

import org.lognet.springboot.grpc.autoconfigure.GRpcServerProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.Hello.HelloRequest;
import com.example.HelloServiceGrpc.HelloServiceStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",
                                                                  GRpcServerProperties.DEFAULT_GRPC_PORT)
                                                      .usePlaintext()
                                                      .build();
        HelloServiceStub helloService = HelloServiceGrpc.newStub(channel);
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
