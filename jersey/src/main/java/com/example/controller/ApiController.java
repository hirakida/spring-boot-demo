package com.example.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.server.ResponseStatusException;

import com.example.entity.User;
import com.example.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Controller
@Path("/")
@RequiredArgsConstructor
public class ApiController {
    private final UserRepository userRepository;

    @GET
    @Path("users")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @GET
    @Path("users/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User findById(@PathParam("id") int id) {
        return userRepository.findById(id)
                             .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @POST
    @Path("users/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User create(User user) {
        return userRepository.save(user);
    }

    @PUT
    @Path("users/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User update(@PathParam("id") int id, User user) {
        return userRepository.save(user);
    }

    @DELETE
    @Path("users/{id}")
    public void delete(@PathParam("id") int id) {
        userRepository.deleteById(id);
    }
}
