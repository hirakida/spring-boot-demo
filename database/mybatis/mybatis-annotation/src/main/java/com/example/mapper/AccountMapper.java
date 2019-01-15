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
import org.apache.ibatis.session.RowBounds;

import com.example.entity.Account;

@Mapper
public interface AccountMapper {

    @Select("SELECT id, name, created_at, updated_at FROM account WHERE id=#{id}")
    Optional<Account> findById(@Param("id") long id);

    default Account findOne(long id) {
        return findById(id).orElseThrow();
    }

    @Select("SELECT id, name, created_at, updated_at FROM account WHERE name=#{name}")
    Account findByName(@Param("name") String name);

    @Select("SELECT id, name, created_at, updated_at FROM account")
    List<Account> findAll(RowBounds rowBounds);

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
