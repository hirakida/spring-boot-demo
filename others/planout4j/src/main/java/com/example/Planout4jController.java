package com.example;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.glassdoor.planout4j.Namespace;
import com.glassdoor.planout4j.NamespaceFactory;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class Planout4jController {
    private static final String NAMESPACE = "namespace";
    private final NamespaceFactory namespaceFactory;

    @GetMapping("/{userId}")
    public Map<String, ?> getParams(@PathVariable int userId) {
        Map<String, ?> overrides = Map.of("datetime", LocalDateTime.now());
        Namespace ns = namespaceFactory.getNamespace(NAMESPACE,
                                                     Map.of("userid", userId),
                                                     overrides)
                                       .get();
        return ns.getParams();
    }
}
