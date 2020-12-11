package com.tournament.managerment.service;

import com.tournament.managerment.dto.CreateTournamentRequestDTO;
import com.tournament.managerment.dto.CreateTournamentResponseDTO;
import com.tournament.managerment.dto.SetMatchResultResponseDTO;
import com.tournament.managerment.dto.TournamentInfoDTO;
import com.tournament.managerment.dto.TournamentListDTO;
import com.tournament.managerment.entity.MatchDO;
import com.tournament.managerment.exception.tournament.*;

public interface MatchService {
	TournamentInfoDTO getTournamentInfo(String tournamentId, String userName) throws TournamentNotFoundException, TeamNotFoundException;

	CreateTournamentResponseDTO createTournament(String userName, CreateTournamentRequestDTO ctr)
            throws InvalidTeamCountException, FormatNotSupportException;

	SetMatchResultResponseDTO setMatchResult(String tourId, Integer tourRound, Integer tourTable,
			MatchDO.Result result) throws MatchNotFoundException;
}
