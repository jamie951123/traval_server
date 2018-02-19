package com.jamie.travel.system.initial.service;

import org.springframework.stereotype.Service;

import com.jamie.travel.core.utils.DirectoryUtils;
import com.jamie.travel.image.service.PhotoPath;

@Service
public class InitialServerImpl implements InitialServer{
	
	private String photo_trip_destination = PhotoPath.photo_trip_destination;
	
	@Override
	public void photo_folder() {
		// TODO Auto-generated method stub
      		DirectoryUtils.createFolder(photo_trip_destination);
	}

}
