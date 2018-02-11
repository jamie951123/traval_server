package com.jamie.travel.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jamie.travel.model.LoginHistory;

@Repository
@Transactional
public interface LoginHistoryDao extends JpaRepository<LoginHistory,Long> {

	LoginHistory findFirstByUserProfileIdOrderByCreateDateDesc(Long id);
}
