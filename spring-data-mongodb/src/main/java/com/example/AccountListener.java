package com.example;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.AfterLoadEvent;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AccountListener extends AbstractMongoEventListener<Account> {

    @Override
    public void onAfterLoad(AfterLoadEvent<Account> event) {
        log.debug("{}", event);
    }

    @Override
    public void onBeforeSave(BeforeSaveEvent<Account> event) {
        log.info("{}", event);
    }

    @Override
    public void onAfterSave(AfterSaveEvent<Account> event) {
        log.info("{}", event);
    }

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Account> event) {
        log.info("{}", event);
    }

    @Override
    public void onAfterDelete(AfterDeleteEvent<Account> event) {
        log.info("{}", event);
    }
}
