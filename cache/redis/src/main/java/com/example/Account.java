package com.example;

import java.io.Serializable;

import org.apache.commons.lang3.RandomStringUtils;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@SuppressWarnings("serial")
public class Account implements Serializable {
    private long id;
    private String name;

    public static Account of(long id) {
        return builder()
                .id(id)
                .name(RandomStringUtils.randomAlphabetic((int)id))
                .build();
    }
}
