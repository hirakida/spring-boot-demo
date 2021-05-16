package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCall.Listener;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;

public class ServerInterceptorImpl implements ServerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(ServerInterceptorImpl.class);

    @Override
    public <REQ, RESP> Listener<REQ> interceptCall(ServerCall<REQ, RESP> call,
                                                   Metadata headers,
                                                   ServerCallHandler<REQ, RESP> next) {
        log.info("{}", call.getMethodDescriptor());
        return next.startCall(call, headers);
    }
}
