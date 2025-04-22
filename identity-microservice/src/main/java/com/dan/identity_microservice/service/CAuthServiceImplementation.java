package com.dan.identity_microservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dan.identity_microservice.entities.CUserCredential;
import com.dan.identity_microservice.entities.models.RInfoUser;
import com.dan.identity_microservice.repository.IUserCredentialRepository;

@Service
public class CAuthServiceImplementation implements IAuthService {
	@Autowired
	private IUserCredentialRepository userCredentialRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private CJwtService jwtService;

	@Override
	public Optional<CUserCredential> saveUser(CUserCredential userCredential) {
		userCredential.setPassword(passwordEncoder.encode(userCredential.getPassword()));

		return Optional.ofNullable(userCredentialRepository.save(userCredential));
	}

	@Override
	public String generateToken(String userName) {
		/*
		 * Para generar el token necesita su nombre de usuario
		 */
		Optional<CUserCredential> user = userCredentialRepository.findByName(userName);

		RInfoUser infoUser = new RInfoUser(
				user.get().getId(),
				user.get().getName(),
				user.get().getSurnames(),
				user.get().getUserName(),
				user.get().getEmail(),
				user.get().getRole());

		return jwtService.generateToken(userName, infoUser);
	}

	@Override
	public void validateToken(String token) {
		/*
		 * Este método valida el token dado
		 */
		jwtService.validateToken(token);
	}
}
