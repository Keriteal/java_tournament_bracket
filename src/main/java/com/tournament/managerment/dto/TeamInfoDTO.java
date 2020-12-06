package com.tournament.managerment.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.annotation.Generated;
import java.util.Collections;

@ApiModel
public final class TeamInfoDTO {
	@ApiModelProperty("队名")
	private String teamName;
	@ApiModelProperty("胜率")
	private double rate;
	@ApiModelProperty("参加的比赛")
	private List<String> joinedTournament;
	@ApiModelProperty("赢下的比赛")
	private List<String> winnedTournament;

	@Generated("SparkTools")
	private TeamInfoDTO(Builder builder) {
		this.teamName = builder.teamName;
		this.rate = builder.rate;
		this.joinedTournament = builder.joinedTournament;
		this.winnedTournament = builder.winnedTournament;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public List<String> getJoinedTournament() {
		return joinedTournament;
	}

	public void setJoinedTournament(List<String> joinedTournament) {
		this.joinedTournament = joinedTournament;
	}

	public List<String> getWinnedTournament() {
		return winnedTournament;
	}

	public void setWinnedTournament(List<String> winnedTournament) {
		this.winnedTournament = winnedTournament;
	}

	@Override
	public String toString() {
		return "TeamInfoDTO [teamName=" + teamName + ", rate=" + rate + ", joinedTournament=" + joinedTournament
				+ ", winnedTournament=" + winnedTournament + "]";
	}

	/**
	 * Creates builder to build {@link TeamInfoDTO}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link TeamInfoDTO}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private String teamName;
		private double rate;
		private List<String> joinedTournament = Collections.emptyList();
		private List<String> winnedTournament = Collections.emptyList();

		private Builder() {
		}

		public Builder withTeamName(String teamName) {
			this.teamName = teamName;
			return this;
		}

		public Builder withRate(double rate) {
			this.rate = rate;
			return this;
		}

		public Builder withJoinedTournament(List<String> joinedTournament) {
			this.joinedTournament = joinedTournament;
			return this;
		}

		public Builder withWinnedTournament(List<String> winnedTournament) {
			this.winnedTournament = winnedTournament;
			return this;
		}

		public TeamInfoDTO build() {
			return new TeamInfoDTO(this);
		}
	}

}
