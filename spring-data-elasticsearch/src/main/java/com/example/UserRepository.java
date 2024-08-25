package com.example;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserRepository extends ElasticsearchRepository<User, String> {
    List<User> findByNameLike(String name);

    List<User> findByMessageLike(String message);

    void deleteByName(String name);
}
