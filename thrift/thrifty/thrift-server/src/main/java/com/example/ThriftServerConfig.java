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

import com.example.thrift.Hello;

@Configuration
public class ThriftServerConfig {

    @Bean(destroyMethod = "stop")
    public TServer server(HelloHandler helloHandler) {
        TServerSocket serverSocket = createServerSocket();
        TServer server = new TSimpleServer(new TSimpleServer.Args(serverSocket)
                                                   .processor(new Hello.Processor<>(helloHandler))
                                                   .protocolFactory(new TBinaryProtocol.Factory()));
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(server::serve);
        return server;
    }

    private static TServerSocket createServerSocket() {
        try {
            return new TServerSocket(8080);
        } catch (TTransportException e) {
            throw new RuntimeException(e);
        }
    }
}
