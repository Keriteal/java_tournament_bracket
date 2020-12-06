package com.tournament.managerment.service;

import com.tournament.managerment.dto.TeamInfoDTO;

public interface TeamService {
	TeamInfoDTO getTeamInfo(String teamName);
}
