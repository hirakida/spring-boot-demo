package com.example;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ViewUtil {

    public static String getMessage() {
        return "message from ViewUtil.";
    }
}
