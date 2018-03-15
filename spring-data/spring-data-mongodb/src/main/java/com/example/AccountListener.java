package com.example;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;

import com.mongodb.DBObject;

@Component
public class AccountListener extends AbstractMongoEventListener<Account> {

    @Override
    public void onBeforeSave(BeforeSaveEvent<Account> event) {
        DBObject dbo = event.getDBObject();
        ZonedDateTime zonedDateTime = LocalDateTime.now().atZone(ZoneId.systemDefault());
        Date date = Date.from(zonedDateTime.toInstant());

        if (event.getSource().getCreatedAt() == null) {
            dbo.put("createdAt", date);
        }
        dbo.put("updatedAt", date);
    }
}
