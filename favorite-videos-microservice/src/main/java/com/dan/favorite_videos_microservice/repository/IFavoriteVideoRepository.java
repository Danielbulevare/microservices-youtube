package com.dan.favorite_videos_microservice.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dan.favorite_videos_microservice.entities.CFavoriteVideo;

@Repository
public interface IFavoriteVideoRepository extends JpaRepository<CFavoriteVideo, UUID>{
	Page<CFavoriteVideo> findByUserId(UUID userId, Pageable page);
	long countByUserId(UUID userId);
}
