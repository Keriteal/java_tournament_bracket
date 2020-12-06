package com.tournament.managerment.dto;

public class SetMatchResultRequestDTO {
	private WinnerEnum winner;

	public WinnerEnum getWinner() {
		return winner;
	}

	public void setWinner(WinnerEnum winner) {
		this.winner = winner;
	}
}
