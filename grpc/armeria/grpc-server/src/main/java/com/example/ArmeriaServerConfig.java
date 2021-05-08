package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.linecorp.armeria.common.grpc.GrpcSerializationFormats;
import com.linecorp.armeria.server.grpc.GrpcService;
import com.linecorp.armeria.spring.ArmeriaServerConfigurator;

import io.grpc.ServerInterceptors;
import io.grpc.protobuf.services.ProtoReflectionService;

@Configuration
public class ArmeriaServerConfig {
    @Bean
    public ArmeriaServerConfigurator armeriaServerConfigurator(HelloService helloService) {
        GrpcService grpcService = GrpcService.builder()
                                             .addService(ServerInterceptors.intercept(helloService,
                                                                                      new ServerInterceptorImpl()))
                                             .addService(ProtoReflectionService.newInstance())
                                             .supportedSerializationFormats(GrpcSerializationFormats.values())
                                             .enableUnframedRequests(true)
                                             .build();
        return builder -> builder.service(grpcService);
    }
}
