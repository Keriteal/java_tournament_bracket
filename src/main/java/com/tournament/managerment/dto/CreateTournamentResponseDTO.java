package com.tournament.managerment.dto;

import javax.annotation.Generated;

public class CreateTournamentResponseDTO {
	private String tournamentId;

	@Generated("SparkTools")
	private CreateTournamentResponseDTO(Builder builder) {
		this.tournamentId = builder.tournamentId;
	}

	public String getTournamentId() {
		return tournamentId;
	}

	public void setTournamentId(String tournamentId) {
		this.tournamentId = tournamentId;
	}

	/**
	 * Creates builder to build {@link CreateTournamentResponseDTO}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link CreateTournamentResponseDTO}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private String tournamentId;

		private Builder() {
		}

		public Builder withTournamentId(String tournamentId) {
			this.tournamentId = tournamentId;
			return this;
		}

		public CreateTournamentResponseDTO build() {
			return new CreateTournamentResponseDTO(this);
		}
	}
}
