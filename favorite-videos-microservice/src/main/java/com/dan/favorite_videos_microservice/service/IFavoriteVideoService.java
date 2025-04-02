package com.dan.favorite_videos_microservice.service;

import java.util.Optional;

import com.dan.favorite_videos_microservice.request.RFavoriteVideo;
import com.dan.favorite_videos_microservice.responses.IResponseFavoriteVideoRepositoryId;

public interface IFavoriteVideoRepositoryService {
	Optional<IResponseFavoriteVideoRepositoryId> saveVideo(RFavoriteVideo favoriteVideo);
}
