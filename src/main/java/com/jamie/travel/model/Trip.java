package com.jamie.travel.model;

import java.io.Serializable;
import java.util.ArrayList;
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
@Table(name = "Trip")
public @Data class Trip extends SecretHome implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tripId", nullable = false, updatable = false)
	private Long tripId;

	@Column(name = "userProfileId", nullable = false)
	private Long userProfileId;

	// Context

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

	@Column(name = "createBy",nullable = false, updatable=false)
	private String createBy;
	//
	@JsonBackReference(value = "userProfile-trip")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userProfileId", insertable = false, updatable = false)
	private UserProfile userProfile;

	@JsonManagedReference(value = "trip-tripShare")
	@OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<TripShare> tripShares;

	@JsonManagedReference(value = "trip-tripImages")
	@OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<TripImage> tripImages;
	
	public void addTripShare(TripShare tripShare) {
		tripShare.setTrip(this);

		// if (tripShares == null) {
		// tripShares = new ArrayList<TripShare>();
		// }
		// tripShares.add(tripShare);
	}

	
	public String getCreateBy() {
		return createBy;
	}


	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}


	public List<TripImage> getTripImages() {
		return tripImages;
	}


	public void setTripImages(List<TripImage> tripImages) {
		this.tripImages = tripImages;
	}


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

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public List<TripShare> getTripShares() {
		return tripShares;
	}

	public void setTripShares(List<TripShare> tripShares) {
		this.tripShares = tripShares;
	}

	@Override
	public String toString() {
		return "Trip [tripId=" + tripId + ", userProfileId=" + userProfileId + ", fromDate=" + fromDate + ", endDate="
				+ endDate + ", tripName=" + tripName + ", tripDesc=" + tripDesc + ", createBy=" + createBy + "]";
	}

}
