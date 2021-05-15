package com.example;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.thrift.HelloClient;
import com.microsoft.thrifty.protocol.BinaryProtocol;
import com.microsoft.thrifty.protocol.Protocol;
import com.microsoft.thrifty.service.AsyncClientBase;
import com.microsoft.thrifty.service.ServiceMethodCallback;
import com.microsoft.thrifty.transport.SocketTransport;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CommandLineRunnerImpl implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        SocketTransport transport = new SocketTransport.Builder("localhost", 8080).build();
        transport.connect();
        Protocol protocol = new BinaryProtocol(transport);
        HelloClient client = new HelloClient(protocol, new AsyncClientListenerImpl());

        client.hello(new ServiceMethodCallbackImpl());

        TimeUnit.MILLISECONDS.sleep(100);
        client.close();
    }

    private static class AsyncClientListenerImpl implements AsyncClientBase.Listener {
        @Override
        public void onTransportClosed() {
            log.info("transportClosed");
        }

        @Override
        public void onError(Throwable error) {
            log.error("{}", error.getMessage(), error);
        }
    }

    private static class ServiceMethodCallbackImpl implements ServiceMethodCallback<String> {
        @Override
        public void onSuccess(String response) {
            log.info("result={}", response);
        }

        @Override
        public void onError(Throwable error) {
            log.error("{}", error.getMessage(), error);
        }
    }
}
