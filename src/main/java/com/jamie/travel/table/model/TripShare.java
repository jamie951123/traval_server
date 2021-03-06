package com.jamie.travel.table.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.ToString;

@ToString
@Entity(name = "TripShare")
@Table(name= "TripShare")
public @Data class TripShare implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "shareId",nullable = false, updatable=false)
	private Long shareId;
	
	@Column(name = "userProfileId",nullable = false)
	private Long userProfileId;
	
	
	@JsonBackReference (value="trip-tripShare")
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tripId")
	private Trip trip;	
	//
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userProfileId", insertable=false, updatable =false)
	private UserProfile userProfile;
	
	public Long getShareId() {
		return shareId;
	}
	public void setShareId(Long shareId) {
		this.shareId = shareId;
	}
	public Long getUserProfileId() {
		return userProfileId;
	}
	public void setUserProfileId(Long userProfileId) {
		this.userProfileId = userProfileId;
	}
	public Trip getTrip() {
		return trip;
	}
	public void setTrip(Trip trip) {
		this.trip = trip;
	}
	public UserProfile getUserProfile() {
		return userProfile;
	}
	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}
	@Override
	public String toString() {
		return "TripShare [shareId=" + shareId + ", userProfileId=" + userProfileId + "]";
	}
	
	
	
	
	
}
