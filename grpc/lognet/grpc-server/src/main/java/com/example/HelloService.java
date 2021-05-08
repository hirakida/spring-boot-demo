package com.example;

import org.lognet.springboot.grpc.GRpcService;

import com.example.Hello.HelloRequest;
import com.example.Hello.HelloResponse;
import com.example.HelloServiceGrpc.HelloServiceImplBase;

import io.grpc.stub.StreamObserver;

@GRpcService(interceptors = ServerInterceptorImpl.class)
public class HelloService extends HelloServiceImplBase {
    @Override
    public void helloUnary(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        HelloResponse response = HelloResponse.newBuilder()
                                              .setMessage("Hello " + request.getName())
                                              .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void helloServerStreaming(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        HelloResponse response = HelloResponse.newBuilder()
                                              .setMessage("Hello " + request.getName())
                                              .build();
        responseObserver.onNext(response);
        responseObserver.onNext(response);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
