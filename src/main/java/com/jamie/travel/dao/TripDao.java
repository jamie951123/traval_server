package com.jamie.travel.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jamie.travel.model.Trip;

@Repository
@Transactional
public interface TripDao extends JpaRepository<Trip,Long> {

}
