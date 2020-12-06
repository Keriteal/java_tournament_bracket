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
	private List<String> teams = new LinkedList<String>();
	private List<RoundInfo> rounds = new LinkedList<RoundInfo>();
	private String winner;
	private Status status;

	@Generated("SparkTools")
	private TournamentInfoDTO(Builder builder) {
		this.tournamentId = builder.tournamentId;
		this.teams = builder.teams;
		this.rounds = builder.rounds;
		this.winner = builder.winner;
		this.status = builder.status;
	}

	public String getTournamentId() {
		return tournamentId;
	}

	public void setTournamentId(String tournamentId) {
		this.tournamentId = tournamentId;
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

	/**
	 * Creates builder to build {@link TournamentInfoDTO}.
	 * 
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link TournamentInfoDTO}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private String tournamentId;
		private List<String> teams = Collections.emptyList();
		private List<RoundInfo> rounds = Collections.emptyList();
		private String winner;
		private Status status;

		private Builder() {
		}

		public Builder withTournamentId(String tournamentId) {
			this.tournamentId = tournamentId;
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

		public TournamentInfoDTO build() {
			return new TournamentInfoDTO(this);
		}
	}

}
