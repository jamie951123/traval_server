package com.jamie.travel.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jamie.travel.model.UserProfile;

@Repository
@Transactional
public interface UserProfileDao extends JpaRepository<UserProfile,Long> {

	//Find
	List<UserProfile> findAll();
	
	UserProfile findByUsername(String username);
	
	UserProfile findByUsernameAndPassword(String username,String password);
	
	@Query("select count (*) from UserProfile where username = ?1")
	int countByUserName(String username);
	
//	@Query("select count (*) from UserProfile where username = ?1 and password =?2")
//	int countByUserNameAndPassword(String username, String password);
	
}
