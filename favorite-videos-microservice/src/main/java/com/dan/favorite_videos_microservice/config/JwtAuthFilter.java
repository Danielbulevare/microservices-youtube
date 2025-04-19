package com.dan.favorite_videos_microservice.config;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthFilter extends OncePerRequestFilter {
	/*
	 * Esta clase es un filtro de seguridad personalizado que intercepta cada solicitud HTTP
	 * para verificar si viene con un token JWT válido. Si es válido, extrae el usuario y sus roles
	 * del token y los registra en el contexto de seguridad de Spring.
	 * 
	 * Esto permite aplicar reglas de autorización basadas en roles dentro de Spring Security.
	 */

	// Clave secreta usada para validar la firma del token JWT
    private final String secret;

    // Constructor que recibe la clave secreta (se inyecta desde SecurityConfig)
    public JwtAuthFilter(String secret) {
        this.secret = secret;
    }

    // Este método se ejecuta por cada solicitud entrante
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

    	// Obtiene la cabecera Authorization del request
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        
        // Si no hay cabecera o no comienza con "Bearer ", la solicitud continúa sin autenticación
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extrae el token quitando el prefijo "Bearer "
        String token = authHeader.substring(7);
        
        try {
        	// Valida y parsea el token usando la clave secreta
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret)))// clave secreta en bytes
                    .build()
                    .parseClaimsJws(token)// valida y analiza el token
                    .getBody();// obtiene el contenido del token (claims)

            // Obtiene el username (subject) del token
            String username = claims.getSubject();
            
            // Obtiene la lista de roles desde los claims del token
            List<String> roles = (List<String>) claims.get("roles");

            // Convierte los roles en autoridades reconocidas por Spring Security
            List<GrantedAuthority> authorities = roles.stream()
                    .map(SimpleGrantedAuthority::new)// cada rol se convierte en SimpleGrantedAuthority
                    .collect(Collectors.toList());

            // Crea el objeto de autenticación con el usuario y sus autoridades
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);

            // Establece la autenticación en el contexto de seguridad
            SecurityContextHolder.getContext().setAuthentication(authToken);
        } catch (Exception e) {
        	// Si algo falla (token inválido, expirado, sin firma válida), devuelve error 401
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido");
            return;
        }

        // Continúa con el resto del filtro o controlador
        filterChain.doFilter(request, response);
    }
}
