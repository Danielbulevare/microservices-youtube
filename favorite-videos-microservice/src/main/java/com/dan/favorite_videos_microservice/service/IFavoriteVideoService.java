package com.dan.favorite_videos_microservice.service;

import java.util.Optional;

import com.dan.favorite_videos_microservice.entities.CFavoriteVideo;
import com.dan.favorite_videos_microservice.error.CVideoAlreadyExistException;

public interface IFavoriteVideoService {
	Optional<CFavoriteVideo> saveVideo(CFavoriteVideo favoriteVideo) throws CVideoAlreadyExistException;
}
