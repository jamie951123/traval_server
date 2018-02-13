package com.jamie.travel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jamie.travel.model.TripImage;

@Repository
@Transactional
public interface TripImageDao extends JpaRepository<TripImage,Long> {

}
