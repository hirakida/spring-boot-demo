package com.example;

import org.lognet.springboot.grpc.autoconfigure.GRpcServerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.CalculatorGrpc.CalculatorBlockingStub;
import com.example.CalculatorOuterClass.CalculatorRequest;
import com.example.CalculatorOuterClass.CalculatorRequest.OperationType;
import com.example.CalculatorOuterClass.CalculatorResponse;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@SpringBootApplication
public class ClientApplication implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(ClientApplication.class);

    @Override
    public void run(String... args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",
                                                                  GRpcServerProperties.DEFAULT_GRPC_PORT)
                                                      .usePlaintext()
                                                      .build();
        CalculatorBlockingStub stub = CalculatorGrpc.newBlockingStub(channel);

        // add
        CalculatorRequest request = CalculatorRequest.newBuilder()
                                                     .setNumber1(1)
                                                     .setNumber2(2)
                                                     .setOperation(OperationType.ADD)
                                                     .build();
        CalculatorResponse response = stub.calculate(request);
        log.info("{} {} {} {}",
                 request.getNumber1(), request.getNumber2(), request.getOperation().name(), response);

        // subtract
        request = CalculatorRequest.newBuilder()
                                   .setNumber1(5)
                                   .setNumber2(3)
                                   .setOperation(OperationType.SUBTRACT)
                                   .build();
        response = stub.calculate(request);
        log.info("{} {} {} {}",
                 request.getNumber1(), request.getNumber2(), request.getOperation().name(), response);

        // multiply
        request = CalculatorRequest.newBuilder()
                                   .setNumber1(2)
                                   .setNumber2(3)
                                   .setOperation(OperationType.MULTIPLY)
                                   .build();
        response = stub.calculate(request);
        log.info("{} {} {} {}",
                 request.getNumber1(), request.getNumber2(), request.getOperation().name(), response);

        // divide
        request = CalculatorRequest.newBuilder()
                                   .setNumber1(5)
                                   .setNumber2(2)
                                   .setOperation(OperationType.DIVIDE)
                                   .build();
        response = stub.calculate(request);
        log.info("{} {} {} {}",
                 request.getNumber1(), request.getNumber2(), request.getOperation().name(), response);
    }

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }
}
