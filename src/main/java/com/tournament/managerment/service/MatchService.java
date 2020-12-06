package com.tournament.managerment.service;

import com.tournament.managerment.dto.CreateTournamentRequestDTO;
import com.tournament.managerment.dto.CreateTournamentResponseDTO;
import com.tournament.managerment.dto.SetMatchResultResponseDTO;
import com.tournament.managerment.dto.TournamentInfoDTO;
import com.tournament.managerment.dto.TournamentListDTO;
import com.tournament.managerment.entity.MatchDO;
import com.tournament.managerment.exception.tournament.InvalidTeamCountException;
import com.tournament.managerment.exception.tournament.MatchNotFoundException;
import com.tournament.managerment.exception.tournament.TournamentNotFoundException;

public interface MatchService {
	TournamentListDTO getTournamentList();

	TournamentInfoDTO getTournamentInfo(String tournamentId) throws TournamentNotFoundException;

	CreateTournamentResponseDTO createTournament(CreateTournamentRequestDTO ctr)
			throws InvalidTeamCountException;

	SetMatchResultResponseDTO setMatchResult(String tourId, Integer tourRound, Integer tourTable,
			MatchDO.Result result) throws MatchNotFoundException;
}
