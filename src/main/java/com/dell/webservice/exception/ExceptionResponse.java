package com.dell.webservice.exception;

import java.util.Date;

public class ExceptionResponse {
	
	String message;
	Date timestamp;
	String status;
	String error;
	
	public ExceptionResponse(String message, Date timestamp, String status, String error) {
		super();
		this.message = message;
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
	}

	public ExceptionResponse() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
}
