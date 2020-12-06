package com.tournament.managerment.dto;

public enum WinnerEnum {
	INVALID(0), TEAM_ONE(1), TEAM_TWO(2);

	private Integer code;

	WinnerEnum(int code) {
		this.code = code;
	}

	public boolean valid() {
		return this.code == 0;
	}
}
