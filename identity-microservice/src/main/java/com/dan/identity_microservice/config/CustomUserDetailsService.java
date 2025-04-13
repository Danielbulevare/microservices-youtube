package com.dan.identity_microservice.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.dan.identity_microservice.entities.CUserCredential;
import com.dan.identity_microservice.repository.IUserCredentialRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private IUserCredentialRepository credentialRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		/*
		 * Nos obliga a a sobrecargar este método. Asi que tomando este nombre de usuario, ve i verifica
		 * en la BD si tienes a ese usuario o no, luego devuelvelo.
		 */
		Optional<CUserCredential> credential =  credentialRepository.findByName(userName);
		
		/*
		 * Una vez que tengamos al usuario, pero como el tipo de retorno es UserDetails, por lo que
		 * necesitamos convertir al objeto credential a UserDetails, para esto se crea una clase que
		 * extienda de UserDetails
		 */
		return credential.map(CustomUserDetails::new).orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado: "+userName));
	}

}
