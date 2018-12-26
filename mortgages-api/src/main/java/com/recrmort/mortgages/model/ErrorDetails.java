package com.recrmort.mortgages.model;

import java.time.LocalDateTime;

public class ErrorDetails {

	private String Message;
	private String details;
	private LocalDateTime timeStamp;

	/**
	 * @param message
	 * @param details
	 * @param timeStamp
	 */
	public ErrorDetails(String message, String details, LocalDateTime timeStamp) {
		super();
		Message = message;
		this.details = details;
		this.timeStamp = timeStamp;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return Message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		Message = message;
	}

	/**
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * @param details the details to set
	 */
	public void setDetails(String details) {
		this.details = details;
	}

	/**
	 * @return the timeStamp
	 */
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

}
