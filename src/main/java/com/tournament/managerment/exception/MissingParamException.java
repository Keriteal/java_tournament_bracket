package com.tournament.managerment.exception;

import org.springframework.http.HttpStatus;

import java.util.Arrays;

public class MissingParamException extends BaseException {
	private static final long serialVersionUID = 1019873614864226620L;

	public MissingParamException(String[] params) {
		super(HttpStatus.BAD_REQUEST, "Missing params: " + Arrays.toString(params));
	}

}
