package com.example;

import org.springframework.graphql.boot.RuntimeWiringCustomizer;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.idl.RuntimeWiring;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserDataWriting implements RuntimeWiringCustomizer {
    private final UserRepository userRepository;

    @Override
    public void customize(RuntimeWiring.Builder builder) {
        builder.scalar(LocalDateTimeScalar.SCALAR_TYPE)
               .type("Query", typeWiring -> typeWiring
                       .dataFetcher("users", env -> userRepository.findAll())
                       .dataFetcher("user", env -> userRepository.findById(getId(env))))
               .type("Mutation", typeWiring -> typeWiring
                       .dataFetcher("createUser", this::createUser)
                       .dataFetcher("deleteUser", this::deleteUser));
    }

    private User createUser(DataFetchingEnvironment env) {
        User user = new User();
        user.setName(env.getArgument("name"));
        return userRepository.save(user);
    }

    private boolean deleteUser(DataFetchingEnvironment env) {
        userRepository.deleteById(getId(env));
        return true;
    }

    private static Integer getId(DataFetchingEnvironment env) {
        String id = env.getArgument("id");
        return Integer.parseInt(id);
    }
}
