package com.example.exception;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.Map;
import java.util.Objects;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.web.server.ResponseStatusException;

@Provider
public class ResponseStatusExceptionMapper implements ExceptionMapper<ResponseStatusException> {
    @Override
    public Response toResponse(ResponseStatusException exception) {
        return Response.status(exception.getStatus().value())
                       .type(APPLICATION_JSON)
                       .entity(Map.of("message", Objects.requireNonNullElse(exception.getMessage(), "")))
                       .build();
    }
}
