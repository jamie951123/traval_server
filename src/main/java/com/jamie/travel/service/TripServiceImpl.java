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
import com.jamie.travel.table.model.Trip;
import com.jamie.travel.table.model.TripShare;
import com.jamie.travel.table.model.UserProfile;
import com.jamie.travel.type.Status;

@Service
public class TripServiceImpl implements TripService {
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
		try {
			if (trip != null) {
				trip.setUserProfileId(userProfile.getUserProfileId());
				trip.setStatus(Status.PROGRESS);
				trip.setCreateDate(new Date());
				trip.setCreateBy(userProfile.getPartyId());

				// Handle Trip Share
				if (trip.getTripShares() != null && trip.getTripShares().size() > 0) {
//					for (int i = 0; i < trip.getTripShares().size(); i++) {
//						TripShare tripShare = trip.getTripShares().get(i);
//						trip.addTripShare(tripShare);
//					}
				} else {
					log.error(LogMsg.errLog("Trip", new String[] { "save", "TripShare must be a least user own" }));
					throw new Exception("The Trip must be a least user own ");
				}
				return tripDao.save(trip);
			} else {
				log.error(LogMsg.errLog("Trip", new String[] { "save", "Error" }));
				throw new Exception("The Trip is Empty ");
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	@Override
	public Trip test(Trip trip) {
		// TODO Auto-generated method stub
		return tripDao.save(trip);
	}

}
