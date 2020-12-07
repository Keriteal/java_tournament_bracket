package com.tournament.managerment.exception.tournament;

import org.springframework.http.HttpStatus;

import com.tournament.managerment.exception.BaseException;

public class TournamentNotFoundException extends BaseException {
	private static final long serialVersionUID = -1922066126936563270L;

	public TournamentNotFoundException(String tournamentId) {
		super(HttpStatus.NOT_FOUND, "TournamentId(" + tournamentId + ") not found");
	}
}
