package com.dan.favorite_videos_microservice.request;

import java.util.UUID;

public record RFavoriteVideo(UUID id, String videoId, String title, String url) {

}
