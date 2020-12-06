package com.tournament.managerment.dto;

import javax.validation.constraints.NotNull;

public class CreateTournamentRequestDTO {
	@NotNull(message ="不能为空")
	private String[] teams;

	public String[] getTeams() {
		return teams;
	}

	public void setTeams(String[] teams) {
		this.teams = teams;
	}
	
}
