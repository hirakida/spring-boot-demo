package com.example;

import static com.example.UserRepository.TABLE_NAME;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.IntStream;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApplicationEventListener {
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS %s";
    private static final String CREATE_TABLE =
            "CREATE TABLE %s (id int, name string) ROW FORMAT DELIMITED FIELDS TERMINATED BY ','";
    private static final String LOAD_DATA =
            "LOAD DATA LOCAL INPATH '/tmp/data/data.txt' OVERWRITE INTO TABLE %s";
    private final Statement statement;
    private final UserRepository userRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void readyEvent() throws SQLException {
        execute(String.format(DROP_TABLE, TABLE_NAME));
        execute(String.format(CREATE_TABLE, TABLE_NAME));
        execute(String.format(LOAD_DATA, TABLE_NAME));

        IntStream.rangeClosed(6, 10)
                 .forEach(i -> userRepository.insert(new User(i, "user" + i)));

        int count = userRepository.count();
        log.info("count: {}", count);
    }

    private void execute(String sql) {
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
