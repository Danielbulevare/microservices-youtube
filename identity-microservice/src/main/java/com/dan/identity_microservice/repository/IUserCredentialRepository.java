package com.dan.identity_microservice.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dan.identity_microservice.entities.CUserCredential;
import java.util.List;


@Repository
public interface IUserCredentialRepository extends JpaRepository<CUserCredential, UUID>{
	Optional<CUserCredential> findByName(String userName);
}
