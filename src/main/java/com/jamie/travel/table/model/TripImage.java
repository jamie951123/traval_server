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
@Entity(name = "TripImages")
@Table(name = "TripImages")
public @Data class TripImage extends SecretHome implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "imageId", nullable = false, updatable = false)
	private Long imageId;
	
	@Column(name = "image", nullable = false, updatable = false)
	private String image;
	
	//
	@JsonBackReference(value = "trip-tripImages")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tripId")
	private Trip trip;

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	@Override
	public String toString() {
		return "TripImages [imageId=" + imageId + ", image=" + image + "]";
	}
	
	
}
