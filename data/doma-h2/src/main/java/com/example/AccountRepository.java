package com.example;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

@ConfigAutowireable
@Dao
public interface AccountRepository {

    @Select
    List<Account> findById(Long id);

    @Select
    List<Account> findAll();

    @Insert
    int insert(Account account);

    @Update
    int update(Account account);

    @Delete
    int delete(Account account);
}
