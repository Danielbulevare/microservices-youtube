package com.dan.favorite_videos_microservice.request;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RFavoriteVideo(
		@NotNull(message = "Por favor especifica el id del usuario.")
		UUID userId,
		
		@NotBlank(message = "Por favor especifica el id del video.")
		String videoId,
		
		@NotBlank(message = "Por favor especifica el título del video.")
		String title,
		
		@NotBlank(message = "Por favor especifica la url imagen del video.")
		String url) {

}
