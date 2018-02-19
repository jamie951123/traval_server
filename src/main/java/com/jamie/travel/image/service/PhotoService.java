package com.jamie.travel.image.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jamie.travel.table.model.PhotoRecord;
import com.jamie.travel.table.model.TripImage;
import com.jamie.travel.type.PhotoAction;

public interface PhotoService {
	 List<TripImage> findAll();
	 boolean uploadPhoto(String base64Image,String partyId);
	 void downloadPhoto(String partyId, String name, HttpServletRequest request, HttpServletResponse response);
	 
	 //Save
	 PhotoRecord saveRecord (PhotoAction photoAction,String partyId,String filePath);
}
