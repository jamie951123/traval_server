package com.jamie.travel.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jamie.travel.controller.UserProfileController;
import com.jamie.travel.dao.TripDao;
import com.jamie.travel.logger.LogMsg;
import com.jamie.travel.model.Trip;
import com.jamie.travel.model.UserProfile;
import com.jamie.travel.type.Status;

@Service
public class TripServiceImpl implements TripService{
	Logger log = LoggerFactory.getLogger(UserProfileController.class);
	@Autowired
	private TripDao tripDao;
	
	@Override
	public List<Trip> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Trip save(UserProfile userProfile, Trip trip) {
		// TODO Auto-generated method stub
		if(trip != null) {
			trip.setUserProfileId(userProfile.getUserProfileId());
			trip.setStatus(Status.PROGRESS);
			trip.setCreateDate(new Date());
			trip.setCreateBy(userProfile.getPartyId());
			trip.getTripShare().get(0).setUserProfileId(userProfile.getUserProfileId());;
			return tripDao.save(trip);
		}else {
			log.error(LogMsg.errLog("Trip", new String[] {"save","Error"}));
			return null;
		}
	}


	
}
