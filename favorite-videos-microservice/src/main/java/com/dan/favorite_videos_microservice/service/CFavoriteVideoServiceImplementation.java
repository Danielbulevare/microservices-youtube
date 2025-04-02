package com.dan.favorite_videos_microservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.dan.favorite_videos_microservice.entities.CFavoriteVideo;
import com.dan.favorite_videos_microservice.error.CVideoAlreadyExistException;
import com.dan.favorite_videos_microservice.repository.IFavoriteVideoRepository;

import jakarta.transaction.Transactional;

@Service
public class CFavoriteVideoServiceImplementation implements IFavoriteVideoService{
	@Autowired
	private IFavoriteVideoRepository favoriteVideoRepository;

	@Transactional
	@Override
	public Optional<CFavoriteVideo> saveVideo(CFavoriteVideo favoriteVideo) throws CVideoAlreadyExistException {
		try {
			CFavoriteVideo cFavoriteVideo= favoriteVideoRepository.save(favoriteVideo);
			return Optional.ofNullable(cFavoriteVideo);			
		} catch (DataIntegrityViolationException e) {
			throw new CVideoAlreadyExistException("El video que intentas registrar ya existe.");
		}
		
	}
	
	
}
