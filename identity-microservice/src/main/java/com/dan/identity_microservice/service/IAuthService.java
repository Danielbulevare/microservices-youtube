package com.dan.identity_microservice.service;

import java.util.Optional;

import com.dan.identity_microservice.entities.CUserCredential;

public interface IAuthService {
	Optional<CUserCredential> saveUser(CUserCredential userCredential);
	String generateToken(String userName);
	void validateToken(String token);
}
