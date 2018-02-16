package com.jamie.travel.image.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jamie.travel.core.utils.DirectoryUtils;
import com.jamie.travel.dao.TripImageDao;
import com.jamie.travel.model.TripImage;

@Service
public class PhotoServiceImpl implements PhotoService{
	Logger log = LoggerFactory.getLogger(this.getClass());

	private String destinationDir = PhotoObject.photo_destination;
	@Autowired
	private TripImageDao tripImageDao;
	
	@Override
	public List<TripImage> findAll() {
		// TODO Auto-generated method stub
		return tripImageDao.findAll();
	}

	@Override
	public boolean uploadPhoto(String base64Image) {
		// TODO Auto-generated method stub
		
//		byte[] data = Base64.decodeBase64(crntImage);
//		try (OutputStream stream = new FileOutputStream("c:/decode/abc.bmp")) {
//		    stream.write(data);
//		}
		return false;
	}

	@Override
	public String downloadPhoto(String partyId, String name) {
		// TODO Auto-generated method stub
		byte[] b;
		try {
			b = DirectoryUtils.pathToByte(destinationDir+name);
			String str = new String(b, StandardCharsets.UTF_8);
			return str;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void initialForder() {
		// TODO Auto-generated method stub
		DirectoryUtils.createFolder(destinationDir);
	}


}
