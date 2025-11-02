package com.example;

import java.util.List;

import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserClient {
    private final ElasticsearchOperations operations;

    public String index(User user) {
        IndexQuery query = new IndexQueryBuilder()
                .withId(user.getId())
                .withObject(user)
                .build();
        return operations.index(query, IndexCoordinates.of("user"));
    }

    public void refresh() {
        operations.indexOps(User.class)
                  .refresh();
    }

    public User get(String id) {
        return operations.get(id, User.class);
    }

    public String delete(String id) {
        return operations.delete(id, User.class);
    }

    public List<SearchHit<User>> search(String name) {
        NativeQuery nativeQuery = new NativeQueryBuilder()
                .withQuery(query -> query.match(match -> match.field("name")
                                                              .query(name)))
                .build();
        SearchHits<User> response = operations.search(nativeQuery, User.class);
        return response.getSearchHits();
    }
}
