package com.example.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    List<Account> findByName(String name);

    List<Account> findByNameLike(String name);

    List<Account> findByNameStartingWith(String name);

    List<Account> findByNameEndingWith(String name);

    List<Account> findByNameContaining(String name);

    List<Account> findByIdLessThan(int id);

    List<Account> findByIdGreaterThan(int id);

    // fieldの値がtrue/falseのentityのみを検索する
//    List<Account> findByActiveTrue(Boolean active);
//    List<Account> findByActiveFalse(Boolean active);
}
