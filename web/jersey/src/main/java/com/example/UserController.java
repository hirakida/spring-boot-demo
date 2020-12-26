package com.example;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Component
@Path("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @GET
    @Produces(APPLICATION_JSON)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(APPLICATION_JSON)
    public User findById(@PathParam("id") int id) {
        return userRepository.findById(id)
                             .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response create(UserRequest request) {
        User user = userRepository.save(request.toUser());
        return Response.created(URI.create("/users/" + user.getId()))
                       .entity(user)
                       .build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public User update(@PathParam("id") int id, UserRequest request) {
        User user = userRepository.findById(id)
                                  .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
        user.setName(request.getName());
        return userRepository.save(user);
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        userRepository.deleteById(id);
        return Response.noContent().build();
    }

    @Data
    public static class UserRequest {
        private String name;

        public User toUser() {
            User user = new User();
            user.setName(name);
            return user;
        }
    }
}
