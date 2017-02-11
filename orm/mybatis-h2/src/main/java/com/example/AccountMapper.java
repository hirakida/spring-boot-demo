package com.example;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AccountMapper {

    String FIND_BY_ID = "select id, name, created_at, updated_at from account where id = #{id}";
    String FIND_BY_NAME = "select id, name, created_at, updated_at from account where name = #{name}";
    String FIND_ALL = "select id, name, created_at, updated_at from account";
    String INSERT = "INSERT into account(name, created_at, updated_at) VALUES(#{name}, NOW(), NOW())";
    String UPDATE = "UPDATE account SET name=#{name}, updated_at=NOW() WHERE id =#{id}";
    String DELETE_BY_ID = "DELETE FROM account WHERE id =#{id}";
    String DELETE_ALL = "DELETE FROM account";

    @Select(FIND_BY_ID)
    Account findById(@Param("id") long id);

    @Select(FIND_BY_NAME)
    Account findByName(@Param("name") String name);

    @Select(FIND_ALL)
    List<Account> findAll();

    @Insert(INSERT)
    @Options(useGeneratedKeys = true)
    int insert(Account account);

    @Update(UPDATE)
    int update(Account account);

    @Delete(DELETE_BY_ID)
    int delete(long id);

    @Delete(DELETE_ALL)
    int deleteAll();
}
