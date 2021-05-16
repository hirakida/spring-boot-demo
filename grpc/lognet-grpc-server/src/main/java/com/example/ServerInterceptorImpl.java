package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;

public class ServerInterceptorImpl implements ServerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(ServerInterceptorImpl.class);

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call,
                                                                 Metadata headers,
                                                                 ServerCallHandler<ReqT, RespT> next) {
        log.info("{}", call.getMethodDescriptor());
        return next.startCall(call, headers);
    }
}
