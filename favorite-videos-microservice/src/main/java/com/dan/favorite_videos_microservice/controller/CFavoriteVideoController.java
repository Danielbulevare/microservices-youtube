package com.dan.favorite_videos_microservice.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.dan.favorite_videos_microservice.entities.CFavoriteVideo;
import com.dan.favorite_videos_microservice.error.CVideoAlreadyExistException;
import com.dan.favorite_videos_microservice.request.RFavoriteVideo;
import com.dan.favorite_videos_microservice.service.IFavoriteVideoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/favorite-video")
public class CFavoriteVideoController {
	@Autowired
	private IFavoriteVideoService favoriteVideoService;

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public Optional<CFavoriteVideo> saveVideo(@Valid @RequestBody RFavoriteVideo favoriteVideo)
			throws CVideoAlreadyExistException {
		return favoriteVideoService.saveVideo(CFavoriteVideo.builder().videoId(favoriteVideo.videoId())
				.userId(favoriteVideo.userId()).title(favoriteVideo.title()).url(favoriteVideo.url()).build());
	}

	@GetMapping("/{userId}/{page}/{records}")
	@ResponseStatus(HttpStatus.OK)
	public List<CFavoriteVideo> findUserVideos(@PathVariable UUID userId, @PathVariable int page,
			@PathVariable int records) {
		return favoriteVideoService.findUserVideos(userId, page, records);
	}
	
	@GetMapping("/{userId}")
	@ResponseStatus(HttpStatus.OK)
	public long totalUserVideos(@PathVariable UUID userId) {
		return favoriteVideoService.totalUserVideos(userId);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteFavoriteVideo(@PathVariable UUID id) {
		favoriteVideoService.deleteFavoriteVideo(id);
	}
}
