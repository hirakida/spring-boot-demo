package com.example;

import org.springframework.data.hazelcast.repository.HazelcastRepository;

public interface UserRepository extends HazelcastRepository<User, String> {
}
