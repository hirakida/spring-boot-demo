package com.example.domain

import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

@Mapper
interface AccountMapper {

    @Select("""
            SELECT id, name, created_at, updated_at FROM account
            WHERE id=#{id}
    """)
    fun findOne(id: Long): Account

    @Select("""
            SELECT id, name, created_at, updated_at FROM account
            where name=#{name}
    """)
    fun findByName(name: String): Account

    @Select("SELECT id, name, created_at, updated_at FROM account")
    fun findAll(): List<Account>

    @Insert("""
            INSERT INTO account(name, created_at, updated_at)
            VALUES(#{name}, NOW(), NOW())
    """)
    @Options(useGeneratedKeys = true)
    fun insert(account: Account): Int

    @Update("""
            UPDATE account SET name=#{name}, updated_at=NOW()
            WHERE id=#{id}
    """)
    fun update(account: Account): Int

    @Delete("DELETE FROM account WHERE id=#{id}")
    fun delete(id: Long): Int

    @Delete("DELETE FROM account")
    fun deleteAll(): Int
}
