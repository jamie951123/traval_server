package com.jamie.travel.image.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jamie.travel.core.utils.DirectoryUtils;
import com.jamie.travel.core.utils.ObjectConverter;
import com.jamie.travel.dao.PhotoRecordDao;
import com.jamie.travel.dao.TripImageDao;
import com.jamie.travel.logger.LogMsg;
import com.jamie.travel.table.model.PhotoRecord;
import com.jamie.travel.table.model.TripImage;
import com.jamie.travel.type.PhotoAction;
import com.jamie.travel.type.Status;

@Service
public class PhotoServiceImpl implements PhotoService {
	Logger log = LoggerFactory.getLogger(this.getClass());

	private String destinationDir = PhotoPath.photo_trip_destination;
	@Autowired
	private TripImageDao tripImageDao;

	@Autowired
	private PhotoRecordDao photoRecordDao;

	@Override
	public List<TripImage> findAll() {
		// TODO Auto-generated method stub
		return tripImageDao.findAll();
	}

	@Override
	public boolean uploadPhoto(String base64Image, String partyId) {
		// TODO Auto-generated method stub
		DirectoryUtils.createFolder(destinationDir + partyId);
		
		String filePath = destinationDir + partyId + "/" + ObjectConverter.folder_sdf.format(new Date());
		String filename = ObjectConverter.folder_sdf.format(new Date());
		try {
			long startTime = System.currentTimeMillis(); // 获取开始时间
			byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
			BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageBytes));

			log.info(LogMsg.infoLog("uploadPhoto", new String[] { "Saving file", filePath }));

			File outputfile = new File(filePath);
			ImageIO.write(img, "png", outputfile);

			long endTime = System.currentTimeMillis(); // 获取结束时间
			log.info(LogMsg.infoLog("uploadPhoto", new String[] { "上传文件共使用时间", String.valueOf(endTime - startTime) }));
			
			saveRecord(PhotoAction.UPLOAD,  partyId, filename);
			return true;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void downloadPhoto(String partyId, String name, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			ServletContext cntx = request.getServletContext();
			// retrieve mimeType dynamically
			log.info(LogMsg.infoLog("downloadPhoto", new String[] { destinationDir + partyId + "/" + name }));
			String mime = cntx.getMimeType(name);
			if (mime == null) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
			response.setContentType(mime);
			File file = new File(destinationDir + partyId + "/" + name);
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
			saveRecord(PhotoAction.DOWNLOAD,  partyId,  name);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	@Override
	public PhotoRecord saveRecord(PhotoAction photoAction, String partyId, String filePath) {
		// TODO Auto-generated method stub
		try {
			PhotoRecord photoRecord = new PhotoRecord();
			photoRecord.setCreateBy(partyId);
			photoRecord.setCreateDate(new Date());
			photoRecord.setFile(filePath);
			photoRecord.setPhotoAction(photoAction);
			photoRecord.setStatus(Status.PROGRESS);
			photoRecord.setMainFile(destinationDir + partyId + "/" + filePath);
			photoRecordDao.save(photoRecord);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return null;
	}

}
