package com.example;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TSSLTransportFactory.TSSLTransportParameters;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.shared.SharedStruct;
import com.example.tutorial.Calculator;
import com.example.tutorial.InvalidOperation;
import com.example.tutorial.Operation;
import com.example.tutorial.Work;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final ThriftProperties properties;

    @Override
    public void run(String... args) throws Exception {
        TTransport transport;
        if (properties.getSsl().isEnabled()) {
            TSSLTransportParameters params = new TSSLTransportParameters();
            params.setTrustStore(properties.getSsl().getTrustStore(),
                                 properties.getSsl().getTrustStorePassword());
            transport = TSSLTransportFactory.getClientSocket(properties.getHost(), properties.getPort(),
                                                             10000, params);
        } else {
            transport = new TSocket(properties.getHost(), properties.getPort());
            transport.open();
        }
        TProtocol protocol = new TBinaryProtocol(transport);
        Calculator.Client client = new Calculator.Client(protocol);

        perform(client);

        transport.close();
    }

    private static void perform(Calculator.Client client) throws TException {
        client.ping();
        log.info("ping()");

        int sum = client.add(1, 1);
        log.info("add({}, {}) result={}", 1, 1, sum);

        Work work = new Work();
        work.op = Operation.DIVIDE;
        work.num1 = 1;
        work.num2 = 0;
        try {
            int result = client.calculate(1, work);
            log.info("calculate({}, {}) result={}", 1, work, result);
        } catch (InvalidOperation io) {
            log.info("Invalid operation: {}", io.why);
        }

        work.op = Operation.SUBTRACT;
        work.num1 = 15;
        work.num2 = 10;
        try {
            int result = client.calculate(1, work);
            log.info("calculate({}, {}) result={}", 1, work, result);
        } catch (InvalidOperation io) {
            log.info("Invalid operation: {}", io.why);
        }

        SharedStruct struct = client.getStruct(1);
        log.info("getStruct() result={}", struct.value);
    }
}
