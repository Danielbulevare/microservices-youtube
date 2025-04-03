package com.dan.favorite_videos_microservice.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.dan.favorite_videos_microservice.entities.CFavoriteVideo;
import com.dan.favorite_videos_microservice.error.CVideoAlreadyExistException;

public interface IFavoriteVideoService {
	Optional<CFavoriteVideo> saveVideo(CFavoriteVideo favoriteVideo) throws CVideoAlreadyExistException;
	List<CFavoriteVideo> findUserVideos(UUID userId, int page, int records);
	long totalUserVideos(UUID userId);
}
