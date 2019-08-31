package com.example;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.StreamUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    public List<User> findAll(Pageable pageable) {
        return userMapper.findAll(toRowBounds(pageable));
    }

    public List<User> findAll() {
        Cursor<User> cursor = userMapper.findAllWithCursor();
        try (cursor) {
            return StreamUtils.createStreamFromIterator(cursor.iterator())
                              .collect(toList());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public User findById(long id) {
        return userMapper.findById(id).orElseThrow();
    }

    public void create(String name) {
        User account = new User();
        account.setName(name);
        userMapper.insert(account);
    }

    public void update(long id, String name) {
        User account = userMapper.findById(id).orElseThrow();
        account.setName(name);
        userMapper.update(account);
    }

    public void delete(long id) {
        userMapper.delete(id);
    }

    private static RowBounds toRowBounds(Pageable pageable) {
        return new RowBounds((int) pageable.getOffset(), pageable.getPageSize());
    }
}
