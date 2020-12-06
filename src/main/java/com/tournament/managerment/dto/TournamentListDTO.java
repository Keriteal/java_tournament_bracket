package com.tournament.managerment.dto;

import java.util.LinkedList;
import java.util.List;

public class TournamentListDTO {
	private List<String> tournaments = new LinkedList<String>();
	
	public TournamentListDTO(List<String> tournaments) {
		this.tournaments = tournaments;
	}

	public void addTournament(String tournamentId) {
		this.tournaments.add(tournamentId);
	}

	public void addTournament(TournamentInfoDTO tournament) {
		this.tournaments.add(tournament.getTournamentId());
	}
	public List<String> getTournaments() {
		return tournaments;
	}

	public void setTournaments(List<String> tournaments) {
		this.tournaments = tournaments;
	}
	
}
