package com.jamie.travel.image.service;

import java.util.List;

import com.jamie.travel.model.TripImage;

public interface PhotoService {
	 List<TripImage> findAll();
	 void initialForder();
	 boolean uploadPhoto(String base64Image);
	 String downloadPhoto(String partyId, String name);
}
