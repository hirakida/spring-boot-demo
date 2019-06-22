package com.example.core;

import java.util.List;

import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserRepository extends ElasticsearchRepository<User, String> {

    List<User> findByName(String name);

    void deleteByName(String name);

    default Iterable<User> searchByMessage(String message) {
        MatchQueryBuilder query = QueryBuilders.matchQuery("message", message);
        return search(query);
    }
}
