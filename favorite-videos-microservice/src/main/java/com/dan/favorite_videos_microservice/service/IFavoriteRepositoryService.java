package com.dan.favorite_videos_microservice.service;

import java.util.Optional;

import com.dan.favorite_videos_microservice.request.RFavoriteVideo;
import com.dan.favorite_videos_microservice.responses.IResponseFavoriteRepositoryId;

public interface IFavoriteRepositoryService {
	Optional<IResponseFavoriteRepositoryId> saveVideo(RFavoriteVideo favoriteVideo);
}
