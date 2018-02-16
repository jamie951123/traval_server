package com.jamie.travel.system.initial.service;

import org.springframework.stereotype.Service;

import com.jamie.travel.core.utils.DirectoryUtils;
import com.jamie.travel.image.service.PhotoObject;

@Service
public class InitialServerImpl implements InitialServer{
	
	private String phtoto_destination = PhotoObject.photo_destination;
	
	@Override
	public void photo_folder() {
		// TODO Auto-generated method stub
		DirectoryUtils.createFolder(phtoto_destination);
	}

}
