package com.example;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TSSLTransportFactory.TSSLTransportParameters;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.tutorial.Calculator;

@Configuration
public class ThriftServerConfig {

    @Bean(destroyMethod = "stop")
    public TServer server(ThriftProperties properties, CalculatorHandler calculatorHandler)
            throws TTransportException, UnknownHostException {
        TServer server = new TSimpleServer(new TSimpleServer.Args(getServerSocket(properties))
                                                   .processor(new Calculator.Processor<>(calculatorHandler))
                                                   .protocolFactory(protocolFactory()));

        ExecutorService executor = Executors.newFixedThreadPool(10);
        executor.execute(server::serve);
        return server;
    }

    @Bean
    public TProtocolFactory protocolFactory() {
        return new TBinaryProtocol.Factory();
    }

    private static TServerSocket getServerSocket(ThriftProperties properties)
            throws TTransportException, UnknownHostException {
        if (properties.getSsl().isEnabled()) {
            TSSLTransportParameters params = new TSSLTransportParameters();
            params.setKeyStore(properties.getSsl().getKeyStore(), properties.getSsl().getKeyStorePassword());
            return TSSLTransportFactory.getServerSocket(properties.getPort(), 10000,
                                                        InetAddress.getByName(properties.getHost()), params);
        } else {
            return new TServerSocket(properties.getPort());
        }
    }
}
