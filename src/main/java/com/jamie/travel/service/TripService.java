package com.jamie.travel.service;

import java.util.List;

import com.jamie.travel.model.Trip;
import com.jamie.travel.model.UserProfile;

public interface TripService {

	List <Trip> findAll();
	Trip save(UserProfile userProfile, Trip trip);
}
