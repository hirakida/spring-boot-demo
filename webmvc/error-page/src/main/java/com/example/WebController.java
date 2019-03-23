package com.example;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class WebController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/400")
    public String badRequest() {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/403")
    public String forbidden() {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    @GetMapping("/404")
    public String notFound() {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/500")
    public String internalServerError() {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
