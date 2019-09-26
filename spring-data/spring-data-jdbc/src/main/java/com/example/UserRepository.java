package com.example;

import java.util.stream.Stream;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("SELECT * FROM user WHERE id = :id")
    User findOne(@Param("id") Integer id);

    @Query("SELECT * FROM user ORDER BY id")
    Stream<User> findAllStream();
}
