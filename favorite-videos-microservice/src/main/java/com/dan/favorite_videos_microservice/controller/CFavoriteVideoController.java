package com.dan.favorite_videos_microservice.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.dan.favorite_videos_microservice.entities.CFavoriteVideo;
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
	public Optional<CFavoriteVideo> saveVideo(@Valid @RequestBody RFavoriteVideo favoriteVideo){
		return favoriteVideoService.saveVideo(CFavoriteVideo.builder()
				.videoId(favoriteVideo.videoId())
				.userId(favoriteVideo.userId())
				.title(favoriteVideo.title())
				.url(favoriteVideo.url())
				.build());
	}
}
