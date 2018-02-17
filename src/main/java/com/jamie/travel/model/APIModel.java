package com.jamie.travel.model;

public class APIModel {
	private boolean status;
	private Object result;
	private String message;
	public APIModel(boolean status, Object result, String message) {
		this.status = status;
		this.result = result;
		this.message = message;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	
	
}
