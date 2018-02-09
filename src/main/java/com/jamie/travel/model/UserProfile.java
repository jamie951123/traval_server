package com.jamie.travel.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.ToString;

@ToString
@Entity(name = "UserProfile")
@Table(name= "UserProfile")
public @Data class UserProfile extends SecretHome implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userProfileId",nullable = false, updatable=false)
	private Long userProfileId;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;

	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@JsonManagedReference 
	@OneToMany(mappedBy="userProfile", cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)
	private List<LoginHistory> loginHistory;	
	
	public List<LoginHistory> getLoginHistory() {
		return loginHistory;
	}

	public void setLoginHistory(List<LoginHistory> loginHistory) {
		this.loginHistory = loginHistory;
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Long getUserProfileId() {
		return userProfileId;
	}

	public void setUserProfileId(Long userProfileId) {
		this.userProfileId = userProfileId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "UserProfile [userProfileId=" + userProfileId + ", username=" + username + ", password=" + password
				+ ", role=" + role + ", loginHistory=" + loginHistory + "]";
	}

	
	

}
