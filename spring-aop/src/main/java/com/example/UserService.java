package com.example;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public List<User> findAll() {
        log.info("findAll");
        return userRepository.findAll();
    }

    public User findById(int id) {
        log.info("findById id={}", id);
        return userRepository.findById(id).orElseThrow();
    }

    public void insert(User user) {
        log.info("insert user={}", user);
        userRepository.save(user);
    }

    public User update(User user) {
        log.info("update {}", user);
        return userRepository.save(user);
    }

    public void deleteAll() {
        log.info("deleteAll");
        userRepository.deleteAll();
    }

    public void deleteById(int id) {
        log.info("deleteById");
        userRepository.deleteById(id);
    }
}
