package com.jamie.travel.model;

public class APIModel {
	private boolean status;
	private Object result;
	
	
	public APIModel(boolean status, Object result) {
		this.status = status;
		this.result = result;
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

}
