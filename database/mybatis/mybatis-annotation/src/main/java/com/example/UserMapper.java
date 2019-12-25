package com.example;

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

@Mapper
public interface UserMapper {
    String FIND_ALL = "SELECT id, name, created_at, updated_at FROM user";

    @Select(FIND_ALL)
    List<User> findAll(RowBounds rowBounds);

    @Select(FIND_ALL)
    Cursor<User> findAllWithCursor();

    @Select(FIND_ALL + " WHERE id=#{id}")
    Optional<User> findById(@Param("id") long id);

    @Select(FIND_ALL + " WHERE name=#{name}")
    User findByName(@Param("name") String name);

    @Insert("INSERT INTO user(name, created_at, updated_at) VALUES(#{name}, NOW(), NOW())")
    @Options(useGeneratedKeys = true)
    int insert(User user);

    @Update("UPDATE user SET name=#{name}, updated_at=NOW() WHERE id=#{id}")
    int update(User user);

    @Delete("DELETE FROM user WHERE id=#{id}")
    int delete(long id);

    @Delete("DELETE FROM user")
    int deleteAll();
}
