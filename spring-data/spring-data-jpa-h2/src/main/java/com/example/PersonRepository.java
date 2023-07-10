package com.example;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    @Query("SELECT u from #{#entityName} u WHERE u.name = ?1")
    List<Person> findByUsername(String name);

    List<Person> findByName(String name);

    List<Person> findByNameLike(String name);

    List<Person> findByNameStartingWith(String name);

    List<Person> findByNameEndingWith(String name);

    List<Person> findByNameContaining(String name);

    List<Person> findByIdLessThan(int id);

    List<Person> findByIdGreaterThan(int id);

    List<Person> findByEnabledTrue();

    List<Person> findByEnabledFalse();
}
