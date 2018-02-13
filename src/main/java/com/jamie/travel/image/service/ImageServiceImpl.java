package com.jamie.travel.image.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jamie.travel.dao.TripImageDao;
import com.jamie.travel.model.TripImage;

@Service
public class ImageServiceImpl implements ImageService{

	@Autowired
	private TripImageDao tripImageDao;
	
	@Override
	public List<TripImage> findAll() {
		// TODO Auto-generated method stub
		return tripImageDao.findAll();
	}

}
