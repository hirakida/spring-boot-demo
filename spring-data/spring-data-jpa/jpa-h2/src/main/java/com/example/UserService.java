package com.example;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public List<User> findAll() {
        return userRepository.findAll(Sort.by(Direction.ASC, "id"));
    }

    public User findById(int id) {
        return userRepository.findById(id)
                             .orElseThrow();
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User update(User request) {
        User user = findById(request.getId());
        user.setName(request.getName());
        return userRepository.save(user);
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

    public void delete(int id) {
        User user = findById(id);
        userRepository.deleteById(user.getId());
    }
}
