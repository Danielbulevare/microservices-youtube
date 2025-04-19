package com.dan.favorite_videos_microservice.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class GatewayHeaderFilter extends OncePerRequestFilter {
	/*
	 * Esta clase es un filtro que valida que la peticion venga desde el API Gateway y no
	 * directamnete desde el puerto donde se ejecuta el microservicio 
	 */

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String forwardedHost = request.getHeader("X-Forwarded-Host");

        // Permite solo si la petición viene del API Gateway
        if (forwardedHost == null || !forwardedHost.contains("localhost:8080")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "⛔ Acceso solo permitido desde el API Gateway");
            return;
        }

        // Si el header existe, continúa con el flujo normal
        filterChain.doFilter(request, response);
    }
}
