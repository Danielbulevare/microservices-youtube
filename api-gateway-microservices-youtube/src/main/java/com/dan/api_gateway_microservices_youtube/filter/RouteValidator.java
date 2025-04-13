package com.dan.api_gateway_microservices_youtube.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Predicate;

@Component
public class RouteValidator {

	/*
	 * Especificamos que si la solicitud viene de alguno de estos endpoints, ignora la validación del
	 * token, ya que estos endpoints se pueden usuar libremente por cualquier cliente
	 */
    public static final List<String> openApiEndpoints = List.of(
            "/auth/register",
            "/auth/token",
            "/eureka"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

}