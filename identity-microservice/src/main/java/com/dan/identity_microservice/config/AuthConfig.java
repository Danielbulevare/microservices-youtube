package com.dan.identity_microservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
public class AuthConfig {

	@Bean
	public UserDetailsService userDetailsService() {
		/*
		 * Crea tu propio servicio de detalle de usuario que se conectará a la BD, y le dará la info.
		 * al proveedor de atenticación, y el proveedor de autenticación puede conectarse nuevamente
		 * al el proveedor de autenticación puede conectarse nuevamente al administrador de autenticación.
		 * Así es como puede autenticar.
		 */
		return new CustomUserDetailsService();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth.requestMatchers(publicEndPoints()).permitAll());

		return http.build();
	}

	private RequestMatcher publicEndPoints() {
		return new OrRequestMatcher(new AntPathRequestMatcher("/api/auth/register"),
				new AntPathRequestMatcher("/api/auth/token"), new AntPathRequestMatcher("/api/auth/validate"));
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public AuthenticationProvider authenticationProvider(){
		/*
		 * Método necesario para autenticar el usuario y contraseña. Al proveedor de autenticación
		 * le estoy el detalle de usuario y el decodificador de la contraseña
		 */
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		/*
		 * Método necesario para autenticar el usuario y contraseña
		 */
		return config.getAuthenticationManager();
	}
}
