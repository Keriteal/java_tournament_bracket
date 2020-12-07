package com.tournament.managerment.dto;

import java.util.LinkedList;
import java.util.List;

public class TournamentListDTO {
	private List<String> tournamentId = new LinkedList<>();
	private List<String> format = new LinkedList<>();

	public TournamentListDTO(){};

	public TournamentListDTO(List<String> tournamentId, List<String> format) {
		this.tournamentId = tournamentId;
		this.format = format;
	}

	public List<String> getTournamentId() {
		return tournamentId;
	}

	public void setTournamentId(List<String> tournamentId) {
		this.tournamentId = tournamentId;
	}

	public List<String> getFormat() {
		return format;
	}

	public void setFormat(List<String> format) {
		this.format = format;
	}
}
