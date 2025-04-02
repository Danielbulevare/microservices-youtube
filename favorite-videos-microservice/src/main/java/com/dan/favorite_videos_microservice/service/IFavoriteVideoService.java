package com.dan.favorite_videos_microservice.service;

import java.util.Optional;

import com.dan.favorite_videos_microservice.entities.CFavoriteVideo;

public interface IFavoriteVideoService {
	Optional<CFavoriteVideo> saveVideo(CFavoriteVideo favoriteVideo);
}
