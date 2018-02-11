package com.jamie.travel.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jamie.travel.dao.LoginHistoryDao;
import com.jamie.travel.dao.UserProfileDao;
import com.jamie.travel.logger.LogMsg;
import com.jamie.travel.model.LoginHistory;
import com.jamie.travel.model.UserProfile;

@Service
public class AdminServiceImpl implements AdminService{
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserProfileDao userProfileDao;
	
	@Autowired
	private LoginHistoryDao loginHistoryDao;
	@Override
	public List<UserProfile> findAll() {
		// TODO Auto-generated method stub
		return userProfileDao.findAll();
	}

	@Override
	public List<LoginHistory> findAllHistory() {
		// TODO Auto-generated method stub
		List<LoginHistory> loginHistorys = loginHistoryDao.findAll();
		if(loginHistorys != null) {
			log.info(LogMsg.infoLog("List<LoginHistory>", new String[] {"[AdminService]-[LoginHistory]- Response() : ",loginHistorys.toString()}));
		}else {
			log.error(LogMsg.errLog("List<LoginHistory>", new String[] {"[AdminService]-[LoginHistory]- Response() : Empty"}));
		}

		return loginHistoryDao.findAll();
	}

	
}
