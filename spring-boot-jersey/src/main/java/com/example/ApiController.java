package com.example;

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

import org.springframework.stereotype.Component;

import com.example.core.Account;
import com.example.core.AccountRepository;

import lombok.RequiredArgsConstructor;

@Component
@Path("/")
@RequiredArgsConstructor
public class ApiController {

    private final AccountRepository accountRepository;

    @GET
    @Path("accounts")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @GET
    @Path("accounts/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Account findOne(@PathParam("id") int id) {
        return accountRepository.findOne(id);
    }

    @POST
    @Path("accounts/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Account create(Account account) {
        return accountRepository.save(account);
    }

    @PUT
    @Path("accounts/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Account update(@PathParam("id") int id, Account account) {
        return accountRepository.save(account);
    }

    @DELETE
    @Path("accounts/{id}")
    public void delete(@PathParam("id") int id) {
        accountRepository.delete(id);
    }
}
