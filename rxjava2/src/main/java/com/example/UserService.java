package com.example;

import java.util.List;

import org.springframework.stereotype.Service;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Service
public class UserService {
    private final List<User> users = List.of(new User(1, "name1"),
                                             new User(2, "name2"),
                                             new User(3, "name3"),
                                             new User(4, "name4"),
                                             new User(5, "name5"));

    public Flowable<User> findAll() {
        return Flowable.fromIterable(users);
    }

    public Maybe<User> findById(int id) {
        return Flowable.fromIterable(users)
                       .filter(user -> user.getId() == id)
                       .firstElement();
    }

    public Single<User> getById(int id) {
        return Flowable.fromIterable(users)
                       .filter(user -> user.getId() == id)
                       .singleOrError();
    }
}
