package com.example.core;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Stream;

import org.seasar.doma.BatchDelete;
import org.seasar.doma.BatchInsert;
import org.seasar.doma.BatchUpdate;
import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.SelectType;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

@ConfigAutowireable
@Dao
public interface AccountRepository {

    @Select
    Optional<Account> findById(Long id);

    @Select
    List<Account> findAll();

    @Select
    List<Account> findAll(SelectOptions options);

    @Select(strategy = SelectType.STREAM)
    List<Account> findAll(Function<Stream<Account>, List<Account>> mapper);

    @Select(strategy = SelectType.COLLECT)
    <RESULT> RESULT findAll(Collector<Account, ?, RESULT> collector);

    @Insert
    int insert(Account account);

    @BatchInsert
    int[] insert(List<Account> accounts);

    @Update
    int update(Account account);

    @Update(sqlFile = true)
    int updateName(Account account);

    @BatchUpdate
    int[] update(List<Account> accounts);

    @Delete
    int delete(Account account);

    @BatchDelete
    int[] delete(List<Account> accounts);

    default Account findOne(Long id) {
        return findById(id)
                .orElseThrow(DataNotFoundException::new);
    }
}
