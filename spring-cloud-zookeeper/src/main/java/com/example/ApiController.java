package com.example;

import java.io.IOException;
import java.util.List;

import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.Participant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final LoadBalancerClient loadBalancer;

    private final DiscoveryClient discovery;

    private final LeaderLatch leaderLatch;

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("/")
    public List<ServiceInstance> index() {
        return discovery.getInstances(appName);
    }

    @GetMapping("/services")
    public List<String> services() {
        return discovery.getServices();
    }

    @GetMapping("/choose")
    public ServiceInstance choose() {
        return loadBalancer.choose(appName);
    }

    @GetMapping("/isLeader")
    public boolean isLeader() {
        return leaderLatch.hasLeadership();
    }

    @GetMapping("/leader")
    public Participant leader() throws Exception {
        return leaderLatch.getLeader();
    }

    @GetMapping("/close")
    public void close() throws IOException {
        leaderLatch.close();
    }
}
