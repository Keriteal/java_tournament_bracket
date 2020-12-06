package com.tournament.managerment.dto;

import java.util.Arrays;

public class RoundInfo {
	private String[] teams;

	public RoundInfo(int count) {
		this.teams = new String[count];
		Arrays.fill(this.teams, ""); //初始化:填充teams数组均为""
	}

	public void setTeam(int index, String team) {
		if (team != null) {
			this.teams[index] = team;
		}
	}

	public String[] getTeams() {
		return this.teams;
	}
}