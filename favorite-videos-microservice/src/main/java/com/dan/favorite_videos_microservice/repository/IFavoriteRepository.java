package com.dan.favorite_videos_microservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dan.favorite_videos_microservice.entities.CFavoriteVideos;

public interface IFavoriteRepository extends JpaRepository<CFavoriteVideos, UUID>{

}
