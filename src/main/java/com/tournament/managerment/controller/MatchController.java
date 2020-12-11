package com.tournament.managerment.controller;

import com.tournament.managerment.exception.tournament.FormatNotSupportException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tournament.managerment.dto.CreateTournamentRequestDTO;
import com.tournament.managerment.dto.CreateTournamentResponseDTO;
import com.tournament.managerment.dto.RestError;
import com.tournament.managerment.dto.SetMatchResultRequestDTO;
import com.tournament.managerment.dto.SetMatchResultResponseDTO;
import com.tournament.managerment.dto.TournamentInfoDTO;
import com.tournament.managerment.dto.TournamentListDTO;
import com.tournament.managerment.dto.WinnerEnum;
import com.tournament.managerment.entity.MatchDO;
import com.tournament.managerment.exception.BaseException;
import com.tournament.managerment.exception.MissingParamException;
import com.tournament.managerment.exception.tournament.InvalidTeamCountException;
import com.tournament.managerment.exception.tournament.MatchNotFoundException;
import com.tournament.managerment.exception.tournament.TournamentNotFoundException;
import com.tournament.managerment.service.MatchService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import javax.validation.Valid;

@RestController
@RequestMapping("/tournament")
@Api(tags = "比赛相关")
@CrossOrigin
public class MatchController {
	private final Logger logger = LoggerFactory.getLogger(MatchController.class);

	private final MatchService matchService;

	@Autowired
	public MatchController(MatchService matchService) {
		this.matchService = matchService;
	}

	@ApiOperation(value = "获取比赛信息")
	@GetMapping(value = "/{id}/{userName}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<TournamentInfoDTO> findTournamentById(@ApiParam("TournamentId") @PathVariable String id,
																@ApiParam("UserName") @PathVariable String userName)
			throws TournamentNotFoundException {
		logger.info("user({}):Get tournament info of {}",userName,id);
		TournamentInfoDTO tournament = matchService.getTournamentInfo(id,userName);

		return ResponseEntity.ok(tournament);
	}

	@ApiOperation(value = "设置某局比赛结果")
	@PostMapping(value = "/{id}/{round}/{table}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<SetMatchResultResponseDTO> setMatchResult(@ApiParam("TournamentId") @PathVariable String id,
																	@ApiParam("Round") @PathVariable Integer round, @ApiParam("Table") @PathVariable Integer table,
																	@RequestBody SetMatchResultRequestDTO req) throws MissingParamException, MatchNotFoundException {
		MatchDO.Result result = MatchDO.Result.NOT_FINISHED;
		logger.info("Set result {} ", req.getWinner());
		if (req.getWinner() == WinnerEnum.TEAM_ONE) {
			result = MatchDO.Result.TEAM_ONE;
		} else if (req.getWinner() == WinnerEnum.TEAM_TWO) {
			result = MatchDO.Result.TEAM_TWO;
		} else {
			String[] params = { "Winner" };

			throw new MissingParamException(params);
		}

		SetMatchResultResponseDTO responseDTO = matchService.setMatchResult(id, round, table, result);
		logger.debug("Set result of {}/{}/{} to team {}", id, round, table, req.getWinner());

		return ResponseEntity.ok(responseDTO);
	}

	// 安排一次锦标赛
	@ApiOperation("安排一次锦标赛")
	@PostMapping(value = "/{userName}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CreateTournamentResponseDTO> createTournament(@ApiParam("UserName") @PathVariable String userName,
																		@RequestBody @Valid CreateTournamentRequestDTO request)
			throws InvalidTeamCountException, FormatNotSupportException {
		logger.info("userName:{} create a tournament",userName);
		CreateTournamentResponseDTO createTournamentResponse = matchService.createTournament(userName, request);
		return ResponseEntity.ok(createTournamentResponse);
	}

	@ExceptionHandler(BaseException.class)
	public ResponseEntity<RestError> handleTournamentNotFound(BaseException ex) {
		return ResponseEntity.status(ex.getStatus()).body(new RestError(ex));
	}

}
