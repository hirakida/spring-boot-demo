package com.example.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.RowBounds;

import com.example.entity.Account;

@Mapper
public interface AccountMapper {
    String FIND_ALL = "SELECT id, name, created_at, updated_at FROM account";
    String FIND_BY_ID = FIND_ALL + " WHERE id=#{id}";
    String FIND_BY_NAME = FIND_ALL + " WHERE name=#{name}";

    @Select(FIND_BY_ID)
    Optional<Account> findById(@Param("id") long id);

    @Select(FIND_BY_NAME)
    Account findByName(@Param("name") String name);

    @Select(FIND_ALL)
    List<Account> findAll(RowBounds rowBounds);

    @Select(FIND_ALL)
    Cursor<Account> findAllWithCursor();

    @Insert("INSERT INTO account(name, created_at, updated_at) VALUES(#{name}, NOW(), NOW())")
    @Options(useGeneratedKeys = true)
    int insert(Account account);

    @Update("UPDATE account SET name=#{name}, updated_at=NOW() WHERE id=#{id}")
    int update(Account account);

    @Delete("DELETE FROM account WHERE id=#{id}")
    int delete(long id);

    @Delete("DELETE FROM account")
    int deleteAll();
}
