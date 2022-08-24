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
    private final UserRepository repository;

    public Page<User> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<User> findAll() {
        return repository.findAll(Sort.by(Direction.ASC, "id"));
    }

    public User findById(int id) {
        return repository.findById(id)
                                 .orElseThrow();
    }

    public User create(User user) {
        return repository.save(user);
    }

    public User update(User request) {
        User user = findById(request.getId());
        user.setName(request.getName());
        return repository.save(user);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public void delete(int id) {
        User user = findById(id);
        repository.deleteById(user.getId());
    }
}
