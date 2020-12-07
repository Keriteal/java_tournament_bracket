package com.tournament.managerment.controller;

import com.tournament.managerment.dto.RestError;
import com.tournament.managerment.exception.BaseException;
import com.tournament.managerment.exception.tournament.TeamNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tournament.managerment.dto.TeamInfoDTO;
import com.tournament.managerment.service.TeamService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/team")
@Api(tags = "队伍相关")
public class TeamController {
	private final Logger logger = LoggerFactory.getLogger(TeamController.class);
	private final TeamService teamService;

	@Autowired
	public TeamController(TeamService teamService) {
		this.teamService = teamService;
	}

	@ApiOperation("获取队伍信息")
	@GetMapping(value = "/{teamName}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<TeamInfoDTO> getTeamByName(@ApiParam("TeamName") @PathVariable String teamName) throws TeamNotFoundException {
		logger.info("Get team info of " + teamName);
		TeamInfoDTO teamInfo = teamService.getTeamInfo(teamName);

		return ResponseEntity.ok(teamInfo);
	}

	@ExceptionHandler(BaseException.class)
	public ResponseEntity<RestError> handleTournamentNotFound(BaseException ex) {
		return ResponseEntity.status(ex.getStatus()).body(new RestError(ex));
	}
}
