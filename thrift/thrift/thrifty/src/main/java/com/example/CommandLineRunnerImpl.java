package com.example;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.tutorial.CalculatorClient;
import com.example.tutorial.Operation;
import com.example.tutorial.Work;
import com.microsoft.thrifty.protocol.BinaryProtocol;
import com.microsoft.thrifty.protocol.Protocol;
import com.microsoft.thrifty.service.AsyncClientBase;
import com.microsoft.thrifty.service.ServiceMethodCallback;
import com.microsoft.thrifty.transport.SocketTransport;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(CommandLineRunnerImpl.class);

    @Override
    public void run(String... args) throws Exception {
        SocketTransport transport = new SocketTransport.Builder("localhost", 9090).build();
        transport.connect();
        Protocol protocol = new BinaryProtocol(transport);
        CalculatorClient client = new CalculatorClient(protocol, new AsyncClientListenerImpl());

        Work work = new Work.Builder()
                .op(Operation.ADD)
                .num1(1)
                .num2(2)
                .build();
        client.calculate(work, new ServiceMethodCallbackImpl());

        TimeUnit.MILLISECONDS.sleep(100);
        client.close();
    }

    private static class AsyncClientListenerImpl implements AsyncClientBase.Listener {
        @Override
        public void onTransportClosed() { }

        @Override
        public void onError(Throwable error) {
            log.error("{}", error.getMessage(), error);
        }
    }

    private static class ServiceMethodCallbackImpl implements ServiceMethodCallback<Integer> {
        @Override
        public void onSuccess(Integer result) {
            log.info("result={}", result);
        }

        @Override
        public void onError(Throwable error) {
            log.error("{}", error.getMessage(), error);
        }
    }
}
