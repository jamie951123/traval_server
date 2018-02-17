package com.jamie.travel.image.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jamie.travel.model.TripImage;

public interface PhotoService {
	 List<TripImage> findAll();
	 boolean uploadPhoto(String base64Image);
	 void downloadPhoto(String partyId, String name, HttpServletRequest request, HttpServletResponse response);
}
