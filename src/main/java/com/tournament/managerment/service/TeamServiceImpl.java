package com.tournament.managerment.service;

import java.util.LinkedList;
import java.util.List;

import com.tournament.managerment.exception.tournament.TeamNotFoundException;
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
	public TeamInfoDTO getTeamInfo(String teamName) throws TeamNotFoundException {
		List<String> joinedTournaments = matchRepository.getJoinedTournamentByTeam(teamName);
		logger.debug("Found {} tournament(s) joined", joinedTournaments.size());
		if (joinedTournaments.size() == 0) {
			throw new TeamNotFoundException(teamName);
		}

		List<String> winnedTournaments = new LinkedList<>();

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
		logger.info("Team {} joined {} tournament(s) and winned {} tournament(s)",
				teamName, joinedTournaments.size(), winnedTournaments.size());
		double rate = (double) winnedTournaments.size() / (double) joinedTournaments.size();
		rate = ((int)(rate*10000+0.5))/10000.0;//保留小数点后四位

		return TeamInfoDTO.builder()
				.withTeamName(teamName)
				.withRate(rate)
				.withJoinedTournament(joinedTournaments)
				.withWinnedTournament(winnedTournaments)
				.build();
	}

}
