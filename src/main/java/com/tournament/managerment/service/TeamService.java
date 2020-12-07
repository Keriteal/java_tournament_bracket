package com.tournament.managerment.service;

import com.tournament.managerment.dto.TeamInfoDTO;
import com.tournament.managerment.exception.tournament.TeamNotFoundException;

public interface TeamService {
	TeamInfoDTO getTeamInfo(String teamName) throws TeamNotFoundException;
}
