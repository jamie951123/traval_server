package com.jamie.travel.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.ToString;

@ToString
@Entity(name = "Trip")
@Table(name= "Trip")
public @Data class Trip extends SecretHome implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tripId",nullable = false, updatable=false)
	private Long tripId;
	
	@Column(name = "userProfileId",nullable = false)
	private Long userProfileId;
	
	
	//Context
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fromDate")
	private Date fromDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "endDate")
	private Date endDate;
	
	@Column(name = "tripName")
	private String tripName;
	
	@Column(name = "tripDesc")
	private String tripDesc;
	
	
	@Column(name = "shareId")
	private Long shareId;
	
	//
	@JsonBackReference (value="userProfile-trip")
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userProfileId", insertable=false, updatable =false)
	private UserProfile userProfile;	
	
	@JsonManagedReference  (value="trip-tripShare")
	@OneToMany(mappedBy="trip", cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)
	private List<TripShare> tripShare;

	public Long getTripId() {
		return tripId;
	}

	public void setTripId(Long tripId) {
		this.tripId = tripId;
	}

	public Long getUserProfileId() {
		return userProfileId;
	}

	public void setUserProfileId(Long userProfileId) {
		this.userProfileId = userProfileId;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	

	public String getTripName() {
		return tripName;
	}

	public void setTripName(String tripName) {
		this.tripName = tripName;
	}

	public String getTripDesc() {
		return tripDesc;
	}

	public void setTripDesc(String tripDesc) {
		this.tripDesc = tripDesc;
	}

	public Long getShareId() {
		return shareId;
	}

	public void setShareId(Long shareId) {
		this.shareId = shareId;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	

	public List<TripShare> getTripShare() {
		return tripShare;
	}

	public void setTripShare(List<TripShare> tripShare) {
		this.tripShare = tripShare;
	}

	@Override
	public String toString() {
		return "Trip [tripId=" + tripId + ", userProfileId=" + userProfileId + ", fromDate=" + fromDate + ", endDate="
				+ endDate + ", tripName=" + tripName + ", tripDesc=" + tripDesc + ", shareId=" + shareId + "]";
	}	
	
	
	
}
