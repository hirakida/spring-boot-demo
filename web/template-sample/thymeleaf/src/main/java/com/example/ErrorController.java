package com.example;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ErrorController {

    @GetMapping("/403")
    public String forbidden() {
        throw new ForbiddenException("forbidden exception");
    }

    @GetMapping("/404")
    public String notFound() {
        throw new DataNotFoundException("not found exception");
    }

    @GetMapping("/500")
    public String serverError() {
        throw new ServerErrorException("server error exception");
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @SuppressWarnings("serial")
    public static class ForbiddenException extends RuntimeException {
        public ForbiddenException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @SuppressWarnings("serial")
    public static class DataNotFoundException extends RuntimeException {
        public DataNotFoundException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @SuppressWarnings("serial")
    public static class ServerErrorException extends RuntimeException {
        public ServerErrorException(String message) {
            super(message);
        }
    }
}
