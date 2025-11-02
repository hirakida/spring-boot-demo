package com.example;

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

@Dao
@ConfigAutowireable
public interface UserRepository {
    @Select
    Optional<User> findById(Long id);

    @Select
    List<User> findAll();

    @Select
    List<User> findAll(SelectOptions options);

    @Select(strategy = SelectType.STREAM)
    List<User> findAll(Function<Stream<User>, List<User>> mapper);

    @Select(strategy = SelectType.COLLECT)
    <RESULT> RESULT findAll(Collector<User, ?, RESULT> collector);

    @Insert
    int insert(User user);

    @BatchInsert
    int[] insert(List<User> users);

    @Update
    int update(User user);

    @Update(sqlFile = true)
    int updateName(User user);

    @BatchUpdate
    int[] update(List<User> users);

    @Delete
    int delete(User user);

    @BatchDelete
    int[] delete(List<User> users);

    default User findOne(Long id) {
        return findById(id).orElseThrow();
    }
}
