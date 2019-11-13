package com.example;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;

import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.Participant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    private final DiscoveryClient discoveryClient;
    private final LeaderLatch leaderLatch;
    private final String serviceId;

    public ApiController(DiscoveryClient discoveryClient,
                         LeaderLatch leaderLatch,
                         @Value("${spring.application.name}") String name) {
        this.discoveryClient = discoveryClient;
        this.leaderLatch = leaderLatch;
        serviceId = name;
    }

    @GetMapping("/")
    public List<ServiceInstance> getInstances() {
        return discoveryClient.getInstances(serviceId);
    }

    @GetMapping("/services")
    public List<String> getServices() {
        return discoveryClient.getServices();
    }

    @GetMapping("/description")
    public String getDescription() {
        return discoveryClient.description();
    }

    @GetMapping("/order")
    public int getOrder() {
        return discoveryClient.getOrder();
    }

    @GetMapping("/leadership")
    public boolean hasLeadership() {
        return leaderLatch.hasLeadership();
    }

    @GetMapping("/leader")
    public Participant getLeader() {
        try {
            return leaderLatch.getLeader();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/close")
    public void close() {
        try {
            leaderLatch.close();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
