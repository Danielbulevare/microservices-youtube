package com.dan.favorite_videos_microservice.entities;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_favorite_videos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CFavoriteVideos {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(nullable = false)
	private UUID id;
	
	@NotBlank(message = "Por favor especifica el id del video.")
	@Length(max = 100, message = "El id del video ha superado los 100 caracteres.")
	@Column(unique = true)
	private String videoId;
	
	@NotBlank(message = "Por favor especifica el título del video.")
	@Length(max = 255, message = "El titulo del video ha superado los 255 caracteres.")
	private String title;
	
	@NotBlank(message = "Por favor especifica el la url de la imagen del video.")
	@Length(max = 255, message = "La url de la imagen del video ha superado los 255 caracteres.")
	private String url;
}
