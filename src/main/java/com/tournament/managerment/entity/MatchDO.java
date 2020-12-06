package com.tournament.managerment.entity;

import javax.annotation.Generated;
import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "match_record")
public class MatchDO {
	public enum Status {
		PENDING, READY, FINISHED
	}

	public enum Result {
		NOT_FINISHED, TEAM_ONE, TEAM_TWO
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int matchId;
	@Column(nullable = false)
	private String tournamentId; //tournament_record表的外键 暂不设置
	@Column(nullable = false)
	private int matchRound;
	@Column(nullable = false)
	private int matchTable;
	@Column(nullable = false)
	private String teamOne;
	@Column(nullable = false)
	private String teamTwo;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Status status;
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private Result result;

	@CreationTimestamp
	private Timestamp timeCreate;
	@UpdateTimestamp
	private Timestamp timeModified;

	public MatchDO() {
	}

	@Generated("SparkTools")
	private MatchDO(Builder builder) {
		this.tournamentId = builder.tournamentId;
		this.matchRound = builder.matchRound;
		this.matchTable = builder.matchTable;
		this.teamOne = builder.teamOne;
		this.teamTwo = builder.teamTwo;
		this.status = builder.status;
		this.result = builder.result;
		this.timeCreate = builder.timeCreate;
		this.timeModified = builder.timeModified;
	}

	public int getMatchId() {
		return matchId;
	}

	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}

	public String getTournamentId() {
		return tournamentId;
	}

	public void setTournamentId(String tournamentId) {
		this.tournamentId = tournamentId;
	}

	public int getRound() {
		return matchRound;
	}

	public void setRound(int round) {
		this.matchRound = round;
	}

	public int getTable() {
		return matchTable;
	}

	public void setTable(int table) {
		this.matchTable = table;
	}

	public String getTeamOne() {
		return teamOne;
	}

	public void setTeamOne(String teamOne) {
		this.teamOne = teamOne;
	}

	public String getTeamTwo() {
		return teamTwo;
	}

	public void setTeamTwo(String teamTwo) {
		this.teamTwo = teamTwo;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public Timestamp getTimeCreate() {
		return timeCreate;
	}

	public void setTimeCreate(Timestamp timeCreate) {
		this.timeCreate = timeCreate;
	}

	public Timestamp getTimeModified() {
		return timeModified;
	}

	public void setTimeModified(Timestamp timeModified) {
		this.timeModified = timeModified;
	}

	@Override
	public String toString() {
		return "MatchDO{" +
				"matchId=" + matchId +
				", tournamentId='" + tournamentId + '\'' +
				", matchRound=" + matchRound +
				", matchTable=" + matchTable +
				", teamOne='" + teamOne + '\'' +
				", teamTwo='" + teamTwo + '\'' +
				", status=" + status +
				", result=" + result +
				", timeCreate=" + timeCreate +
				", timeModified=" + timeModified +
				'}';
	}

	/** b
	 * 	 * Createsuilder to build {@link MatchDO}.
	 *
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link MatchDO}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private int matchId;
		private String tournamentId;
		private int matchRound;
		private int matchTable;
		private String teamOne;
		private String teamTwo;
		private Status status;
		private Result result;
		private Timestamp timeCreate;
		private Timestamp timeModified;

		private Builder() {
		}

		public Builder withMatchId(int matchId) {
			this.matchId=matchId;
			return this;
		}

		public Builder withTournamentId(String tournamentId) {
			this.tournamentId = tournamentId;
			return this;
		}

		public Builder withTourRound(int matchRound) {
			this.matchRound = matchRound;
			return this;
		}

		public Builder withTourTable(int matchTable) {
			this.matchTable = matchTable;
			return this;
		}

		public Builder withTeamOne(String teamOne) {
			this.teamOne = teamOne;
			return this;
		}

		public Builder withTeamTwo(String teamTwo) {
			this.teamTwo = teamTwo;
			return this;
		}

		public Builder withStatus(Status status) {
			this.status = status;
			return this;
		}

		public Builder withResult(Result result) {
			this.result = result;
			return this;
		}

		public Builder withTimeCreate(Timestamp timeCreate) {
			this.timeCreate = timeCreate;
			return this;
		}

		public Builder withTimeModified(Timestamp timeModified) {
			this.timeModified = timeModified;
			return this;
		}

		public MatchDO build() {
			return new MatchDO(this);
		}
	}
}
