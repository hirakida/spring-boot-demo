package com.example;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.tutorial.Calculator;
import com.example.tutorial.InvalidOperation;
import com.example.tutorial.Operation;
import com.example.tutorial.Work;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(CommandLineRunnerImpl.class);

    @Override
    public void run(String... args) throws Exception {
        TTransport transport = new TSocket("localhost", 9090);
        transport.open();
        TProtocol protocol = new TBinaryProtocol(transport);
        Calculator.Client client = new Calculator.Client(protocol);

        perform(client);

        transport.close();
    }

    private static void perform(Calculator.Client client) throws TException {
        Work work = new Work();

        work.op = Operation.ADD;
        work.num1 = 1;
        work.num2 = 2;
        int result = client.calculate(work);
        log.info("calculate({}) result={}", work, result);

        work.op = Operation.SUBTRACT;
        work.num1 = 15;
        work.num2 = 10;
        result = client.calculate(work);
        log.info("calculate({}) result={}", work, result);

        work.op = Operation.DIVIDE;
        work.num1 = 10;
        work.num2 = 2;
        try {
            result = client.calculate(work);
            log.info("calculate({}) result={}", work, result);
        } catch (InvalidOperation io) {
            log.error("Invalid operation: {}", io.why);
        }
    }
}
