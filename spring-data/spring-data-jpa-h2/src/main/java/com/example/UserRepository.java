package com.example;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u from #{#entityName} u WHERE u.name = ?1")
    List<User> findByUsername(String name);

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
