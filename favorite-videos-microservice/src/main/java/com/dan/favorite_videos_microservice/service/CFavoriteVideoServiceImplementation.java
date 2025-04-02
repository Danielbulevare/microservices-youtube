package com.dan.favorite_videos_microservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dan.favorite_videos_microservice.entities.CFavoriteVideo;
import com.dan.favorite_videos_microservice.repository.IFavoriteVideoRepository;

import jakarta.transaction.Transactional;

@Service
public class CFavoriteVideoServiceImplementation implements IFavoriteVideoService{
	@Autowired
	private IFavoriteVideoRepository favoriteVideoRepository;

	@Transactional
	@Override
	public Optional<CFavoriteVideo> saveVideo(CFavoriteVideo favoriteVideo) {
		return Optional.ofNullable(favoriteVideoRepository.save(favoriteVideo));
	}
	
	
}
