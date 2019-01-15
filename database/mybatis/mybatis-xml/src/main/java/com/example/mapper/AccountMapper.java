package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.entity.Account;

@Mapper
public interface AccountMapper {

    Account findOne(long id);

    List<Account> findAll();
}
