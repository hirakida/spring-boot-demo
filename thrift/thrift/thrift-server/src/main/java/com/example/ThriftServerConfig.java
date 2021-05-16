package com.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.tutorial.Calculator;

@Configuration
public class ThriftServerConfig {

    @Bean(destroyMethod = "stop")
    public TServer server(CalculatorHandler calculatorHandler) {
        TServer server = new TSimpleServer(new TSimpleServer.Args(createServerSocket())
                                                   .processor(new Calculator.Processor<>(calculatorHandler))
                                                   .protocolFactory(new TBinaryProtocol.Factory()));
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(server::serve);
        return server;
    }

    private static TServerSocket createServerSocket() {
        try {
            return new TServerSocket(9090);
        } catch (TTransportException e) {
            throw new RuntimeException(e);
        }
    }
}
