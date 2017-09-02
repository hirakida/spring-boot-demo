package com.example;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends ElasticsearchRepository<Account, String> {

    List<Account> findByName(String name);
}
