package com.example;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {

    Account findOne(long id);

    List<Account> findAll();
}
