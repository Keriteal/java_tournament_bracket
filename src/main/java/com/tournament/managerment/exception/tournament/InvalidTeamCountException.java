package com.tournament.managerment.exception.tournament;

import org.springframework.http.HttpStatus;

import com.tournament.managerment.exception.BaseException;

public class InvalidTeamCountException extends BaseException {
	private static final long serialVersionUID = -2310054206639152651L;

	public InvalidTeamCountException(int teamCount) {
		super(HttpStatus.ACCEPTED, "Invalid team count: " + teamCount);
	}

}
