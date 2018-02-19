package com.jamie.travel.table.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jamie.travel.type.PhotoAction;

import lombok.ToString;

@ToString
@Entity(name = "PhotoRecord")
@Table(name = "PhotoRecord")
public class PhotoRecord extends SecretHome implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "recordId", nullable = false, updatable = false)
	private Long recordId;

	@Column(name = "createBy", nullable = false, updatable = false)
	private String createBy;

	@Column(name = "mainFile", nullable = false, updatable = false)
	private String mainFile;

	@Column(name = "file", nullable = false, updatable = false)
	private String file;

	@Column(name = "photoAction", nullable = false)
	@Enumerated(EnumType.STRING)
	private PhotoAction photoAction;

	
	public String getMainFile() {
		return mainFile;
	}

	public void setMainFile(String mainFile) {
		this.mainFile = mainFile;
	}

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public PhotoAction getPhotoAction() {
		return photoAction;
	}

	public void setPhotoAction(PhotoAction photoAction) {
		this.photoAction = photoAction;
	}

	@Override
	public String toString() {
		return "PhotoRecord [recordId=" + recordId + ", createBy=" + createBy + ", mainFile=" + mainFile + ", file="
				+ file + ", photoAction=" + photoAction + "]";
	}

}
