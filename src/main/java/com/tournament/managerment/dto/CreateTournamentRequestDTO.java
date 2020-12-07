package com.tournament.managerment.dto;

import com.tournament.managerment.entity.TournamentDO;

import javax.validation.constraints.NotNull;

public class CreateTournamentRequestDTO {
	@NotNull(message ="不能为空")
	private String[] teams;
	private String format;

	public String[] getTeams() {
		return teams;
	}

	public void setTeams(String[] teams) {
		this.teams = teams;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
}
