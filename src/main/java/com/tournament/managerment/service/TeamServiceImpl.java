package com.tournament.managerment.service;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tournament.managerment.dto.TeamInfoDTO;
import com.tournament.managerment.entity.MatchDO;
import com.tournament.managerment.repository.MatchRepository;

@Service
public class TeamServiceImpl implements TeamService {
	private final static Logger logger = LoggerFactory.getLogger(TeamServiceImpl.class);
	private final MatchRepository matchRepository;

	@Autowired
	public TeamServiceImpl(MatchRepository matchRepository) {
		this.matchRepository = matchRepository;
	}

	@Override
	public TeamInfoDTO getTeamInfo(String teamName) {
		List<String> joinedTournaments = matchRepository.getJoinedTournamentByTeam(teamName);
		List<String> winnedTournaments = new LinkedList<String>();
		logger.debug("Found {} joined and {} winned", joinedTournaments.size(), winnedTournaments.size());

		for (String tournamentId : joinedTournaments) {
			MatchDO match = matchRepository.getLastMatchByTournamentId(tournamentId);
			logger.debug("---------------");
			logger.debug(match.toString());

			if (match.getResult() == MatchDO.Result.TEAM_ONE && match.getTeamOne().equals(teamName)) {
				logger.debug("Team {} winned in {} as team one", teamName, tournamentId);
				winnedTournaments.add(tournamentId);
			} else if (match.getResult() == MatchDO.Result.TEAM_TWO && match.getTeamTwo().equals(teamName)) {
				logger.debug("Team {} winned in {} as team two", teamName, tournamentId);
				winnedTournaments.add(tournamentId);
			}
		}
		logger.info("Team {} joined {} matches and winned {} matches",
				teamName, joinedTournaments.size(), winnedTournaments.size());
		double rate = (double) winnedTournaments.size() / (double) joinedTournaments.size();

		return TeamInfoDTO.builder()
				.withTeamName(teamName)
				.withRate(rate)
				.withJoinedTournament(joinedTournaments)
				.withWinnedTournament(winnedTournaments)
				.build();
	}

}
