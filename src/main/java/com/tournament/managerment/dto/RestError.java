package com.tournament.managerment.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class RestError {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime timestamp;
	private String message;
	private String debugMessage;

	public RestError() {
		this.timestamp = LocalDateTime.now();
	}

	public RestError(Throwable ex) {
		this();
		this.message = ex.getMessage();
		this.setDebugMessage(ex.getLocalizedMessage());
	}

	public RestError(Throwable ex, String msg) {
		this();
		this.message = msg;
		this.setDebugMessage(ex.getLocalizedMessage());
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDebugMessage() {
		return debugMessage;
	}

	public void setDebugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
	}
}
