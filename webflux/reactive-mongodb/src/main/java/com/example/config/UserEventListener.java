package com.example.config;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import org.bson.Document;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;

import com.example.entity.User;

public class UserEventListener extends AbstractMongoEventListener<User> {

    @Override
    public void onBeforeSave(BeforeSaveEvent<User> event) {
        final Document document = event.getDocument();
        if (document != null) {
            if (event.getSource().getCreatedAt() == null) {
                document.put("createdAt", Date.from(Instant.now()));
            }
            document.put("updatedAt", Date.from(Instant.now()));
        }
    }
}
