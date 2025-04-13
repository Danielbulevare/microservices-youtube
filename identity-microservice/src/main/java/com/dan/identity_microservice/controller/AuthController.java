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
		/*
		 * Le decimos al authenticationManager que por favor autentique el usuario y contraseña;
		 * te estoy dando el nombre de usuario y contraseña para que realices esta autenticación. Si
		 * encuentras que el usuario esta presente en mi BD con las credenciales que te acabo de decir,
		 * entonces devuelveme el token.
		 * 
		 * Ahora, el administrador de autenticación necesita hablar con mi BD, por lo que necesita la
		 * ayuda de alguien (UserDetailService). Asi que no basta con solo poner esta declaración en el
		 * código para autenticar al usuario. Necesitamos definir una clase UserDetailService que se
		 * conectara a la BD
		 */
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.userName(), authRequest.password()));
		
		//Si la auntenticacion es correcta, devuelve el token
		if(authentication.isAuthenticated()) {			
			return authService.generateToken(authRequest.userName());
		}else {
			throw new RuntimeException("Acceso invalido.");
		}
	}
	
	@GetMapping("/validate")
	@ResponseStatus(HttpStatus.OK)
	public boolean validateToken(@RequestParam("token") String token) {
		/*
		 * Si no hay excepciones, devolvera un true indicando que el token es válido. Si ocurre una
		 * excepción, se obtendra el valor correcto del error, es decir si el token de validación
		 * no tiene un valor correcto, obtendrá la excepción, pero si es correcto obtendrás el true
		 * como respuesta
		 */
		authService.validateToken(token);
		return true;
	}
}
