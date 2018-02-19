package com.jamie.travel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jamie.travel.table.model.PhotoRecord;

@Repository
@Transactional
public interface PhotoRecordDao extends JpaRepository<PhotoRecord,Long> {
	
}
