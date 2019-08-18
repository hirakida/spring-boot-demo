package com.example;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByName(String name);

    List<User> findByNameLike(String name);

    List<User> findByNameStartingWith(String name);

    List<User> findByNameEndingWith(String name);

    List<User> findByNameContaining(String name);

    List<User> findByIdLessThan(int id);

    List<User> findByIdGreaterThan(int id);

    List<User> findByEnabledTrue();

    List<User> findByEnabledFalse();
}
