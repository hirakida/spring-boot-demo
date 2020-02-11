package com.example.mapper;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import java.util.Map;
import java.util.Objects;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.dao.EmptyResultDataAccessException;

@Provider
public class EmptyResultDataAccessExceptionMapper implements ExceptionMapper<EmptyResultDataAccessException> {
    @Override
    public Response toResponse(EmptyResultDataAccessException exception) {
        return Response.status(NOT_FOUND)
                       .type(APPLICATION_JSON)
                       .entity(Map.of("message", Objects.requireNonNullElse(exception.getMessage(), "")))
                       .build();
    }
}
