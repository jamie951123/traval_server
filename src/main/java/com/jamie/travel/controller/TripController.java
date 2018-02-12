package com.jamie.travel.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jamie.travel.core.utils.ObjectUtils;
import com.jamie.travel.jwt.security.TokenObject;
import com.jamie.travel.jwt.security.ValidateTokenService;
import com.jamie.travel.model.Trip;
import com.jamie.travel.model.TripShare;
import com.jamie.travel.model.UserProfile;
import com.jamie.travel.service.TripService;
import com.jamie.travel.type.Status;

@RequestMapping(value = TokenObject.MAINAPI + "/authentication/trip")
@Controller
public class TripController {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TripService tripService;

	@Autowired
	private ValidateTokenService validateTokenService;

	@Transactional(rollbackFor = Exception.class)
	@GetMapping(value = "/test", produces = "application/json;charset=UTF-8")
	public @ResponseBody Trip test(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Trip trip = new Trip();
		trip.setCreateBy("11.TL");
		trip.setCreateDate(new Date());
		trip.setStatus(Status.PROGRESS);
		trip.setUserProfileId(new Long(1));

		TripShare tripShare = new TripShare();
		tripShare.setUserProfileId(new Long(1));

		trip.addTripShare(tripShare);

		TripShare tripShare2 = new TripShare();
		tripShare2.setUserProfileId(new Long(1));
		trip.addTripShare(tripShare2);

		Trip newTrip = tripService.test(trip);
		return newTrip;
	}

	@Transactional(rollbackFor = Exception.class)
	@PostMapping(value = "/save", produces = "application/json;charset=UTF-8")
	public @ResponseBody Trip save(HttpServletRequest request, HttpServletResponse response, @RequestBody Trip trip)
			throws IOException {
		UserProfile u = validateTokenService.token_getUserProfile(request.getHeader(TokenObject.HEADER_USERTOKEN));
//		if (trip == null) {
//			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "RequestBody is empty");
//		}
		if (u != null && ObjectUtils.isNotNullEmpty(u.getPartyId()) && u.getUserProfileId() != null) {
			try {
				return tripService.save(u, trip);
			} catch (Exception e) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, (e.getMessage()));
				return null;
			}

		} else {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Wrong Token");
			return null;
		}


	}
}
