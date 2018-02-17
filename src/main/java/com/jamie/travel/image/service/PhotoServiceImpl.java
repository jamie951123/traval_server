package com.jamie.travel.image.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jamie.travel.core.utils.DirectoryUtils;
import com.jamie.travel.core.utils.ObjectConverter;
import com.jamie.travel.core.utils.ObjectUtils;
import com.jamie.travel.dao.TripImageDao;
import com.jamie.travel.jwt.security.TokenObject;
import com.jamie.travel.logger.LogMsg;
import com.jamie.travel.model.APIModel;
import com.jamie.travel.model.TripImage;
import com.jamie.travel.model.UserProfile;

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
	public void downloadPhoto(String partyId, String name, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
				ServletContext cntx = request.getServletContext();
				String filename = destinationDir + name;
				// retrieve mimeType dynamically
				log.info(LogMsg.infoLog("PhotoPath", new String[] {filename}));
				String mime = cntx.getMimeType(filename);
				if (mime == null) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				}
				response.setContentType(mime);
				File file = new File(filename);
				response.setContentLength((int) file.length());

				FileInputStream in = new FileInputStream(file);
				OutputStream out = response.getOutputStream();

				// Copy the contents of the file to the output stream
				byte[] buf = new byte[1024];
				int count = 0;
				while ((count = in.read(buf)) >= 0) {
					out.write(buf, 0, count);
				}
				out.close();
				in.close();

		
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}
	

}
