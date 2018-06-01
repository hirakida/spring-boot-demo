package com.example.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

    public List<User> findAll() {
        log.info("findAll");
        return Arrays.asList(new User(1, "name1"),
                             new User(2, "name2"),
                             new User(3, "name3"));
    }

    public User findById(long id) {
        log.info("findById id={}", id);
        return new User(id, "name" + id);
    }
}
