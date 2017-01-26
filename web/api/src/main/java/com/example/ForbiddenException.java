package com.example;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ResponseStatusを指定しているので、この例外が投げられると403が返る
 * ResponseStatusを指定しなかった場合は500が返る
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
@SuppressWarnings("serial")
public class ForbiddenException extends RuntimeException {

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(Throwable cause) {
        super(cause);
    }

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }
}
