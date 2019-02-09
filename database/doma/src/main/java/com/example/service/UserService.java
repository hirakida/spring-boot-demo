package com.example.service;

import java.util.List;

import org.seasar.doma.boot.Pageables;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> findAll(Pageable pageable) {
        return userRepository.findAll(Pageables.toSelectOptions(pageable));
    }

    public User findOne(long id) {
        return userRepository.findOne(id);
    }

    public int create(String name, int age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        return userRepository.insert(user);
    }

    public int update(long id, String name, int age) {
        User user = userRepository.findOne(id);
        user.setName(name);
        user.setAge(age);
        return userRepository.update(user);
    }

    public int delete(long id) {
        User user = userRepository.findOne(id);
        return userRepository.delete(user);
    }
}
