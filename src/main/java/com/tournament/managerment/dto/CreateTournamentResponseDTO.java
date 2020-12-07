package com.tournament.managerment.dto;

import javax.annotation.Generated;

public class CreateTournamentResponseDTO {
	private String tournamentId;
	private String userName;

	public String getTournamentId() {
		return tournamentId;
	}

	public void setTournamentId(String tournamentId) {
		this.tournamentId = tournamentId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public static Builder builder() {
		return new Builder();
	}


	public static final class Builder {
		private String tournamentId;
		private String userName;

		private Builder() {
		}

		public static Builder aCreateTournamentResponseDTO() {
			return new Builder();
		}

		public Builder withTournamentId(String tournamentId) {
			this.tournamentId = tournamentId;
			return this;
		}

		public Builder withUserName(String userName) {
			this.userName = userName;
			return this;
		}

		public CreateTournamentResponseDTO build() {
			CreateTournamentResponseDTO createTournamentResponseDTO = new CreateTournamentResponseDTO();
			createTournamentResponseDTO.setTournamentId(tournamentId);
			createTournamentResponseDTO.setUserName(userName);
			return createTournamentResponseDTO;
		}
	}
}
