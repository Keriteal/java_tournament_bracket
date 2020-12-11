package com.tournament.managerment.dto;

import java.util.LinkedList;
import java.util.List;
import javax.annotation.Generated;
import java.util.Collections;

public class TournamentInfoDTO {
	public enum Status {
		MATCHING, ENDED
	}

	private String tournamentId;
	private String format;
	private List<String> teams = new LinkedList<>();
	private List<RoundInfo> rounds = new LinkedList<>();
	private String winner;
	private Status status;
	private boolean isHosted;
	private List<TeamInfoDTO> teamInfo = new LinkedList<>();

	public TournamentInfoDTO() {}

	public TournamentInfoDTO(String tournamentId, String format, List<String> teams, List<RoundInfo> rounds, String winner, Status status, boolean isHosted, List<TeamInfoDTO> teamInfo) {
		this.tournamentId=tournamentId;
		this.format=format;
		this.teams=teams;
		this.rounds=rounds;
		this.winner=winner;
		this.status=status;
		this.isHosted=isHosted;
		this.teamInfo=teamInfo;
	}

	public String getTournamentId() {
		return tournamentId;
	}

	public void setTournamentId(String tournamentId) {
		this.tournamentId = tournamentId;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public List<String> getTeams() {
		return teams;
	}

	public void setTeams(List<String> teams) {
		this.teams = teams;
	}

	public List<RoundInfo> getRounds() {
		return rounds;
	}

	public void setRounds(List<RoundInfo> rounds) {
		this.rounds = rounds;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public boolean isHosted() {
		return isHosted;
	}

	public void setHosted(boolean hosted) {
		isHosted = hosted;
	}

	public List<TeamInfoDTO> getTeamInfo() {
		return teamInfo;
	}

	public void setTeamInfo(List<TeamInfoDTO> teamInfo) {
		this.teamInfo = teamInfo;
	}

	public static Builder builder() {
		return new Builder();
	}


	public static final class Builder {
		private String tournamentId;
		private String format;
		private List<String> teams = new LinkedList<>();
		private List<RoundInfo> rounds = new LinkedList<>();
		private String winner;
		private Status status;
		private boolean isHosted;
		private List<TeamInfoDTO> teamInfo = new LinkedList<>();

		private Builder() {
		}

		public static Builder aTournamentInfoDTO() {
			return new Builder();
		}

		public Builder withTournamentId(String tournamentId) {
			this.tournamentId = tournamentId;
			return this;
		}

		public Builder withFormat(String format) {
			this.format = format;
			return this;
		}

		public Builder withTeams(List<String> teams) {
			this.teams = teams;
			return this;
		}

		public Builder withRounds(List<RoundInfo> rounds) {
			this.rounds = rounds;
			return this;
		}

		public Builder withWinner(String winner) {
			this.winner = winner;
			return this;
		}

		public Builder withStatus(Status status) {
			this.status = status;
			return this;
		}

		public Builder withIsHosted(boolean isHosted) {
			this.isHosted = isHosted;
			return this;
		}

		public Builder withTeamInfo(List<TeamInfoDTO> teamInfo) {
			this.teamInfo = teamInfo;
			return this;
		}

		public TournamentInfoDTO build() {
			return new TournamentInfoDTO(tournamentId, format, teams, rounds, winner, status, isHosted, teamInfo);
		}
	}
}
