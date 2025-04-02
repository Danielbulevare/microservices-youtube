package com.dan.favorite_videos_microservice.error;

@SuppressWarnings("serial")
public class CVideoAlreadyExistException extends Exception {
	public CVideoAlreadyExistException(String message) {
		super(message);
	}
}
