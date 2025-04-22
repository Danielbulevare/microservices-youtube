package com.dan.identity_microservice.entities.models;

import java.util.UUID;

import com.dan.identity_microservice.enums.ERole;

public record RInfoUser(UUID id, String name, String surnames, String userName, String email, ERole role) {

}
