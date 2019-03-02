package com.example.config;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.AfterLoadEvent;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;

import com.example.entity.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserListener extends AbstractMongoEventListener<User> {

    @Override
    public void onAfterLoad(AfterLoadEvent<User> event) {
        log.debug("{}", event);
    }

    @Override
    public void onBeforeSave(BeforeSaveEvent<User> event) {
        log.info("{}", event);
    }

    @Override
    public void onAfterSave(AfterSaveEvent<User> event) {
        log.info("{}", event);
    }

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<User> event) {
        log.info("{}", event);
    }

    @Override
    public void onAfterDelete(AfterDeleteEvent<User> event) {
        log.info("{}", event);
    }
}
