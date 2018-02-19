package com.jamie.travel.service;

import java.util.List;

import com.jamie.travel.table.model.Trip;
import com.jamie.travel.table.model.UserProfile;

public interface TripService {

	List <Trip> findAll();
	Trip save(UserProfile userProfile, Trip trip);
	
	Trip test(Trip trip);
}
