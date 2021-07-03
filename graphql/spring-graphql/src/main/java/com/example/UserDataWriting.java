package com.example;

import org.springframework.graphql.boot.RuntimeWiringBuilderCustomizer;
import org.springframework.stereotype.Component;

import com.example.scalars.ExtendedScalars;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.idl.RuntimeWiring;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserDataWriting implements RuntimeWiringBuilderCustomizer {
    private final UserRepository userRepository;

    @Override
    public void customize(RuntimeWiring.Builder builder) {
        builder.scalar(ExtendedScalars.LOCAL_DATE_TIME)
               .type("Query", typeWiring -> typeWiring
                       .dataFetcher("users", env -> userRepository.findAll())
                       .dataFetcher("user", env -> userRepository.findById(getId(env)))
                       .dataFetcher("searchUser", env -> userRepository.findByName(env.getArgument("name"))))
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
