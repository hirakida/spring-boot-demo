package com.example;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    List<Account> findByName(String name);

    List<Account> findByNameLike(String name);

    List<Account> findByNameStartingWith(String name);

    List<Account> findByNameEndingWith(String name);

    List<Account> findByNameContaining(String name);

    List<Account> findByIdLessThan(int id);

    List<Account> findByIdGreaterThan(int id);

    List<Account> findByEnabledTrue();

    List<Account> findByEnabledFalse();
}
