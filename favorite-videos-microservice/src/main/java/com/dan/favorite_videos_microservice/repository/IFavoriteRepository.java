package com.dan.favorite_videos_microservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dan.favorite_videos_microservice.entities.CFavoriteVideos;

@Repository
public interface IFavoriteRepository extends JpaRepository<CFavoriteVideos, UUID>{

}
