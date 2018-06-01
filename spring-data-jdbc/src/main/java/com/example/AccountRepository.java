package com.example;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends CrudRepository<Account, Integer> {

    @Query("SELECT * FROM account WHERE id = :id")
    Account findOne(@Param("id") Integer id);

    @Query("SELECT * FROM account ORDER BY id")
    Stream<Account> findAllStream();

    @Query("SELECT * FROM account ORDER BY id")
    List<Account> findAllList();
}
