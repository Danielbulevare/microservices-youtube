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
		Optional<CUserCredential> credential =  credentialRepository.findByName(userName);
		return credential.map(CustomUserDetails::new).orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado: "+userName));
	}

}
