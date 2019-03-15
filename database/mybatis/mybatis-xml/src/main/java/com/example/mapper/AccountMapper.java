package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.entity.Account;

@Mapper
public interface AccountMapper {

    List<Account> findAll();

    List<Account> findByIds(List<Long> ids);

    Account findById(long id);
}
