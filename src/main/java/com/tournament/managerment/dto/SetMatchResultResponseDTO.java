package com.tournament.managerment.dto;

import javax.annotation.Generated;

public class SetMatchResultResponseDTO {
	private String winner;
	private boolean grouped;

	@Generated("SparkTools")
	private SetMatchResultResponseDTO(Builder builder) {
		this.winner = builder.winner;
		this.grouped = builder.grouped;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public boolean isGrouped() {
		return grouped;
	}

	public void setGrouped(boolean grouped) {
		this.grouped = grouped;
	}

	/**
	 * Creates builder to build {@link SetMatchResultResponseDTO}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link SetMatchResultResponseDTO}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private String winner;
		private boolean grouped;

		private Builder() {
		}

		public Builder withWinner(String winner) {
			this.winner = winner;
			return this;
		}

		public Builder withGrouped(boolean grouped) {
			this.grouped = grouped;
			return this;
		}

		public SetMatchResultResponseDTO build() {
			return new SetMatchResultResponseDTO(this);
		}
	}

}
