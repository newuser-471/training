package com.example.gateway.security;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouterValidator {

    public static final List<String> openApiEndpoint = List.of(
            "/auth/register",
            "/auth/login",
            "/eureka",
            "/api-docs"
    );

    public Predicate<ServerHttpRequest> isSecured = request->openApiEndpoint.stream().noneMatch(uri->request.getURI().getPath().contains(uri));
}
