package com.example.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.entity.User;

public interface UserRepository extends ElasticsearchRepository<User, String> {

    List<User> findByName(String name);

    void deleteByName(String name);
}
