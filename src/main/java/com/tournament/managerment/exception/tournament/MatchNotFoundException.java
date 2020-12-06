package com.tournament.managerment.exception.tournament;

import org.springframework.http.HttpStatus;

import com.tournament.managerment.exception.BaseException;

public class MatchNotFoundException extends BaseException {
	private static final long serialVersionUID = 3778714198340287765L;

	public MatchNotFoundException(String tourId, Integer tourRound, Integer tourTable) {
		super(HttpStatus.NOT_FOUND, "Match " + tourId + "." + tourRound + "." + tourTable);
	}

}
