package com.tournament.managerment.service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.tournament.managerment.entity.TournamentDO;
import com.tournament.managerment.exception.tournament.FormatNotSupportException;
import com.tournament.managerment.repository.TournamentRepository;
import com.tournament.managerment.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tournament.managerment.dto.CreateTournamentRequestDTO;
import com.tournament.managerment.dto.CreateTournamentResponseDTO;
import com.tournament.managerment.dto.RoundInfo;
import com.tournament.managerment.dto.SetMatchResultResponseDTO;
import com.tournament.managerment.dto.TournamentInfoDTO;
import com.tournament.managerment.dto.TournamentListDTO;
import com.tournament.managerment.entity.MatchDO;
import com.tournament.managerment.entity.MatchDO.Result;
import com.tournament.managerment.entity.MatchDO.Status;
import com.tournament.managerment.exception.tournament.InvalidTeamCountException;
import com.tournament.managerment.exception.tournament.MatchNotFoundException;
import com.tournament.managerment.handler.BracketSocketEventHandler;
import com.tournament.managerment.repository.MatchRepository;
import com.tournament.managerment.util.UuidUtil;

@Service
public class MatchServiceImpl implements MatchService {
	private final static Logger logger = LoggerFactory.getLogger(MatchServiceImpl.class);

	private final UserRepository userRepository;
	private final TournamentRepository tournamentRepository;
	private final MatchRepository matchRepository;
	private final BracketSocketEventHandler socketHandler;

	@Autowired
	public MatchServiceImpl(UserRepository userRepository, TournamentRepository tournamentRepository, MatchRepository matchRepository, BracketSocketEventHandler socketHandler) {
		this.userRepository=userRepository;
		this.tournamentRepository = tournamentRepository;
		this.matchRepository = matchRepository;
		this.socketHandler = socketHandler;
	}


	@Override
	public TournamentInfoDTO getTournamentInfo(String tournamentId) {

		String format = tournamentRepository.getFormatByTournamentId(tournamentId);
		List<MatchDO> matches = matchRepository.findMatchByTournamentId(tournamentId);
		List<String> teams = matchRepository.getTeamsByTournamentId(tournamentId);

		int lastRound = -1;
		if (teams.indexOf("") != -1) {
			teams.remove(teams.indexOf(""));
		}
		if (teams.indexOf(null) != -1) {
			teams.remove(teams.indexOf(null));
		}
		String winner = "";
		TournamentInfoDTO.Status status = TournamentInfoDTO.Status.ENDED;

		// Get status
		for (MatchDO match : matches) {
			if (match.getStatus() != MatchDO.Status.FINISHED) {
				status = TournamentInfoDTO.Status.MATCHING;
			}
			lastRound = Math.max(match.getRound(), lastRound);
		}
		logger.info("tournamentId:{} lastRound:{}", tournamentId, lastRound);

		// Get tournament bracket
		RoundInfo[] rounds = new RoundInfo[lastRound + 1];
		if(format.equals("SINGLE")){
			int tableCount = 1;
			for (int round = lastRound; round >= 0; round--) {  //初始化rounds
				rounds[round] = new RoundInfo(tableCount);
				tableCount = tableCount * 2;
			}
			for (MatchDO match : matches) {
				rounds[match.getRound() - 1].setTeam((match.getTable() - 1) * 2, match.getTeamOne());
				rounds[match.getRound() - 1].setTeam((match.getTable() - 1) * 2 + 1, match.getTeamTwo());
			}
		}
		else if(format.equals("CONSOLATION")) {
			//
		}
		else {
			//
		}

		// Get winner
		if (status == TournamentInfoDTO.Status.ENDED) {
			MatchDO lastMatch = matchRepository.getLastMatchByTournamentId(tournamentId);
			if (lastMatch.getResult() == Result.TEAM_ONE) {
				winner = lastMatch.getTeamOne();
			} else if (lastMatch.getResult() == Result.TEAM_TWO) {
				winner = lastMatch.getTeamTwo();
			} else {
				winner = "";
			}
		}

		TournamentInfoDTO info = TournamentInfoDTO.builder()
				.withTournamentId(tournamentId)
				.withFormat(format)
				.withRounds(Arrays.asList(rounds))
				.withTeams(teams)
				.withStatus(status)
				.withWinner(winner)
				.build();
		return info;
	}

	@Override
	public CreateTournamentResponseDTO createTournament(String userName, CreateTournamentRequestDTO ctr)
			throws InvalidTeamCountException, FormatNotSupportException {
		String[] teamNames = ctr.getTeams();
		int teamCount = Array.getLength(ctr.getTeams());

		//队伍数量输入错误2^n
		if ((teamCount & (teamCount - 1)) != 0) {
			throw new InvalidTeamCountException(teamCount);
		}

		String uuid = UuidUtil.getUuid();
		logger.debug("Generated UUID: {}", uuid);
		//判断赛制
		if(ctr.getFormat().equals("SINGLE")) {
			List<MatchDO> matches = new LinkedList<>();
			int tableCount = teamCount / 2;
			for (int round = 1; round <= Math.log(teamCount) + 1; round++) {
				for (int table = 1; table <= tableCount; table++) {
					MatchDO.Builder matchBuilder = MatchDO.builder();
					matchBuilder.withTournamentId(uuid)
							.withTourRound(round)
							.withTourTable(table)
							.withStatus(Status.PENDING)
							.withResult(Result.NOT_FINISHED)
							.build();
					if (round == 1) {
						matchBuilder
								.withStatus(Status.READY)
								.withTeamOne(teamNames[2 * (table - 1)])
								.withTeamTwo(teamNames[2 * table - 1]);
					}
					matches.add(matchBuilder.build());
				}
				tableCount = tableCount / 2;
			}
			logger.info("created a tournament with matches:{}",matches);
			matchRepository.saveAll(matches);

			int userId=userRepository.getUserIdByUserName(userName);
			TournamentDO.TournamentBuilder tournamentBuilder = TournamentDO.builder();
			tournamentBuilder.withTournamentId(uuid)
					.withFormat(ctr.getFormat())
					.withUserId(userId)
					.build();
			tournamentRepository.save(tournamentBuilder.build());
		}
		else if(ctr.getFormat().equals("CONSOLATION")) {
			throw new FormatNotSupportException(ctr.getFormat());
		}
		else {
			throw new FormatNotSupportException(ctr.getFormat());
		}

		return CreateTournamentResponseDTO.builder()
				.withTournamentId(uuid)
				.withUserName(userName)
				.build();
	}

	@Override
	public SetMatchResultResponseDTO setMatchResult(String tourId, Integer tourRound, Integer tourTable,
			MatchDO.Result result) throws MatchNotFoundException {
		MatchDO match = matchRepository.findMatch(tourId, tourRound, tourTable);
		boolean success = false;
		String winner = null;
		if (match == null) {
			throw new MatchNotFoundException(tourId, tourRound, tourTable);
		}
		if (match.getStatus() == Status.READY) {
			success = matchRepository.setResult(tourId, tourRound, tourTable, result) == 1;
		}
		switch (result) {
		case TEAM_ONE:
			winner = match.getTeamOne();
			break;
		case TEAM_TWO:
			winner = match.getTeamTwo();
			break;
		default:
			break;
		}
		logger.info("Set match result {}", success);
		logger.info("Winner: {}", winner);
		if (success && winner != null) {
			if (tourTable % 2 == 1) {
				logger.info("Set match {}.{}.{} result team one to {}",
						tourId, tourRound + 1, tourTable / 2 + 1, winner);
				success = matchRepository.setTeamOne(tourId, tourRound + 1, tourTable / 2 + 1, winner) == 1;
			} else {
				logger.info("Set match {}.{}.{} result team two to {}",
						tourId, tourRound + 1, tourTable / 2, winner);
				success = matchRepository.setTeamTwo(tourId, tourRound + 1, tourTable / 2, winner) == 1;
			}
		}
		socketHandler.sendUpdatedToAll(getTournamentInfo(tourId).getRounds());
		return SetMatchResultResponseDTO.builder()
				.withWinner(winner)
				.withGrouped(false)
				.build();
	}

}
