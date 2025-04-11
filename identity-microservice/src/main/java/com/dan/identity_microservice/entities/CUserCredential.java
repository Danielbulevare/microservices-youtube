package com.dan.identity_microservice.entities;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import com.dan.identity_microservice.enums.ERole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_users_credentials")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CUserCredential {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(nullable = false)
	private UUID id;
	
	@NotBlank(message = "Por favor especifica el nombre.")
	@Length(max = 50, message = "El nombre ha superado los 50 caracteres.")
	private String name;
	
	@NotBlank(message = "Por favor especifica los apellidos.")
	@Length(max = 50, message = "Los apellidos superan los 50 caracteres.")
	private String surnames;
	
	@NotBlank(message = "Por favor especifica el nombre de usuario.")
	@Length(max = 50, message = "El nombre de usuario supera los 50 caracteres.")
	private String userName;
	
	@NotBlank(message = "Por favor especifica el correo.")
	@Length(max = 100, message = "El correo ha superado los 100 caracteres.")
	@Email(message = "Por favor introduce un correo válido.")
	@Column(unique = true)
	private String email;
	
	@NotBlank(message = "Por favor especifica la contraseña.")
	@Length(max = 255, message = "La contraseña ha superado los 255 cracteres.")
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ERole role;
}
