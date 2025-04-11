package com.dan.identity_microservice.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import com.dan.identity_microservice.entities.CUserCredential;
import com.dan.identity_microservice.requests.RAuthRequest;
import com.dan.identity_microservice.service.IAuthService;

@RestController
@RequestMapping("api/auth")
public class AuthController {
	@Autowired
	private IAuthService authService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.OK)
	public Optional<CUserCredential> saveUser(@RequestBody CUserCredential userCredential){
		return authService.saveUser(userCredential);
	}
	
	@PostMapping("/token")
	@ResponseStatus(HttpStatus.OK)
	public String generateToken(@RequestBody RAuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.userName(), authRequest.password()));
		
		if(authentication.isAuthenticated()) {			
			return authService.generateToken(authRequest.userName());
		}else {
			throw new RuntimeException("Acceso invalido.");
		}
	}
	
	@GetMapping("/validate")
	@ResponseStatus(HttpStatus.OK)
	public boolean validateToken(@RequestParam("token") String token) {
		authService.validateToken(token);
		return true;
	}
}
