package com.tournament.managerment.exception;

import org.springframework.http.HttpStatus;

public abstract class BaseException extends Exception {
	private static final long serialVersionUID = 3638881318937974052L;
	
	private HttpStatus status;
	
	public BaseException(HttpStatus status, String msg) {
		super(msg);
		this.setStatus(status);
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
}
